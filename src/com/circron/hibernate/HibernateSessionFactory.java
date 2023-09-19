package com.circron.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import javax.persistence.Entity;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Configures and provides access to Hibernate sessions, tied to the current
 * thread of execution. Follows the Thread Local Session pattern, see
 */
public class HibernateSessionFactory implements ServletContextListener {
    private static final Log log = LogFactory.getLog(HibernateSessionFactory.class);
    private static final String CONFIG_FILE_LOCATION = "applicationContext-hibernate.xml";
    private static final Object BUILD_CONNECTION_LOCK = new Object();
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();

    private static org.hibernate.SessionFactory sessionFactory;
    private static String configFile = CONFIG_FILE_LOCATION;

    public HibernateSessionFactory() {}

    /**
     * Returns the ThreadLocal Session instance. Lazy initialize the
     * <code>SessionFactory</code> if needed.
     *
     * @return Session
     */
    public static Session getSession() throws HibernateException {
        Session session = threadLocal.get();

        if (session == null || !session.isOpen()) {
            if (sessionFactory == null || sessionFactory.isClosed()) {
                synchronized (BUILD_CONNECTION_LOCK) {
                    if (sessionFactory == null || sessionFactory.isClosed()) {
                        rebuildSessionFactory();
                    }
                }
            }
            session = sessionFactory != null ? sessionFactory.openSession() : null;
            threadLocal.set(session);
        }
        return session;
    }

    /**
     * Rebuild hibernate session factory
     *
     */
    public static void rebuildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(configFile);

            log.debug("Searching for Entity classes.");
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

            int i = 0;
            for (BeanDefinition bd : scanner.findCandidateComponents("com.circron")) {
                String name = bd.getBeanClassName();
                log.debug("Entity Class Found:  " + name);

                configuration.addAnnotatedClass(Class.forName(name));
                i++;
            }
            log.info(i + " Entity Classes Found.");
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException | ClassNotFoundException e) {
            log.error("Error Creating SessionFactory", e);
        }
    }

    /**
     * Close the single hibernate session instance.
     */
    public static void closeSession() throws HibernateException {
        Session session = threadLocal.get();
        threadLocal.set(null);

        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    /**
     * return session factory
     *
     */
    public static org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * return session factory
     *
     * session factory will be rebuilt in the next call
     */
    public static void setConfigFile(String configFile) {
        HibernateSessionFactory.configFile = configFile;
        sessionFactory = null;
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            log.info("Session Factory Shutting Down.");
            sessionFactory.close();
            while (!sessionFactory.isClosed()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.warn("Session Factory Shutdown Interrupted");
                }
            }

            log.info("Session Factory Shut Down.");
        } catch (HibernateException e) {
            log.error("Error well closing session factory.", e);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            log.info("Session Factory Starting Up");
            Class.forName("org.mariadb.jdbc.Driver");
            synchronized (BUILD_CONNECTION_LOCK) {
                rebuildSessionFactory();
            }
            log.info("Session Factory Started");
        } catch (ClassNotFoundException e) {
            log.error("MariaDB driver class not found.", e);
        }
    }
}