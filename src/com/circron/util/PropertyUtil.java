package com.circron.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * Loads and caches properties files for use by the application at large.
 *
 * @author cngann
 *
 */
public class PropertyUtil {
    private static final Log log = LogFactory.getLog(PropertyUtil.class);
    private static final Object SINGLETON_LOCK = new Object();
    private static PropertyUtil singleton;

    private final Map<String, Map<String, String>> propertyMap = new HashMap<>();

    private PropertyUtil() {}

    public static PropertyUtil getInstance() {
        if (singleton == null) {
            synchronized (SINGLETON_LOCK) {
                if (singleton == null) {
                    singleton = new PropertyUtil();
                }
            }
        }
        return singleton;
    }

    /**
     * Loads all properties of the given category into memory.
     */
    private synchronized void loadCategory(String category) {
        try {
            log.debug("Loading property category '" + category + "'");
            if (!propertyMap.containsKey(category)) {
                Properties props = new Properties();
                props.load(this.getClass().getResourceAsStream("/" + category + ".properties"));
                Map<String, String> map = new HashMap<>();
                propertyMap.put(category.toLowerCase(), map);
                for (Entry<Object, Object> entry : props.entrySet()) {
                    map.put(entry.getKey().toString().trim().toLowerCase(), entry.getValue().toString().trim());
                    log.debug("Loaded property '" + category + "." + entry.getKey().toString().trim() + "' = '" + entry.getValue().toString().trim() + "'");
                }
            }
        } catch (IOException e) {
            log.error("Error loading properties file '" + category + "'", e);
        }
    }

    /**
     * Gets the given property's value from the given category.
     */
    public String get(String category, String property) {
        return get(category, property, null);
    }

    /**
     * Gets the given property's value from the given category. Should the
     * category or property be unavailable the given fallback is returned.
     */
    public String get(String category, String property, String fallback) {
        if (!propertyMap.containsKey(category.toLowerCase())) {
            loadCategory(category.toLowerCase());
        }
        if (!propertyMap.containsKey(category.toLowerCase())) {
            return fallback;
        }

        if (propertyMap.get(category.toLowerCase()).get(property.toLowerCase()) != null) {
            return propertyMap.get(category.toLowerCase()).get(property.toLowerCase());
        }

        return fallback;
    }

    /**
     * Get a map of all properties in the given category mapped to their value.
     */
    public Map<String, String> getAll(String category) {
        if (!propertyMap.containsKey(category)) {
            loadCategory(category);
        }
        return Collections.unmodifiableMap(propertyMap.get(category));
    }

    /**
     * Returns the set all properties available in the given category.
     */
    public Set<String> getProperties(String category) {

        if (!propertyMap.containsKey(category)) {
            loadCategory(category);
        }
        return Collections.unmodifiableSet(propertyMap.get(category).keySet());
    }

    /**
     * Gets the given property's value from the given category as a long.
     */
    public Long getLong(String category, String property) {
        return getLong(category, property, null);
    }

    /**
     * Gets the given property's value from the given category as a long. Should
     * the category or property be unavailable the given fallback is returned.
     */
    public Long getLong(String category, String property, Long fallback) {
        try {
            return Long.valueOf(get(category, property));
        } catch (Throwable t) {
            if (fallback == null) {
                log.error("Error parsing '" + property + "' in '" + category + "'", t);
            } else {
                log.warn("Error parsing '" + property + "' in '" + category + "' using default.");
            }
            return fallback;
        }
    }

    /**
     * Gets the given property's value from the given category as an integer.
     */
    public Integer getInt(String category, String property) {
        return getInt(category, property, null);
    }

    /**
     * Gets the given property's value from the given category as an integer.
     * Should the category or property be unavailable the given fallback is
     * returned.
     */
    public Integer getInt(String category, String property, Integer fallback) {
        try {
            return Integer.valueOf(get(category, property));
        } catch (Throwable t) {
            if (fallback == null) {
                log.error("Error parsing '" + property + "' in '" + category + "'", t);
            } else {
                log.warn("Error parsing '" + property + "' in '" + category + "' using default.");
            }
            return fallback;
        }
    }

    /**
     * Gets the given property's value from the given category as a boolean.
     */
    public Boolean getBoolean(String category, String property) {
        return getBoolean(category, property, null);
    }

    /**
     * Gets the given property's value from the given category as a boolean.
     * Should the category or property be unavailable the given fallback is
     * returned.
     */
    public Boolean getBoolean(String category, String property, Boolean fallback) {
        try {
            return Boolean.valueOf(get(category, property));
        } catch (Throwable t) {
            if (fallback == null) {
                log.error("Error parsing '" + property + "' in '" + category + "'", t);
            } else {
                log.warn("Error parsing '" + property + "' in '" + category + "' using default.");
            }
            return fallback;
        }
    }

    /**
     * Gets the given property's value from the given category as a file.
     */
    public File getFile(String category, String property) {
        return getFile(category, property, null);
    }

    /**
     * Gets the given property's value from the given category as a file. Should
     * the category or property be unavailable the given fallback is returned.
     */
    public File getFile(String category, String property, File fallback) {
        try {
            return new File(get(category, property));
        } catch (Throwable t) {
            if (fallback == null) {
                log.error("Error parsing '" + property + "' in '" + category + "'", t);
            } else {
                log.warn("Error parsing '" + property + "' in '" + category + "' using default.");
            }
            return fallback;
        }
    }

    /**
     * Gets the given property's value from the given category as an array.
     */
    public String[] getArray(String category, String property) {
        return getArray(category, property, null);
    }

    /**
     * Gets the given property's value from the given category as an array.
     * Should the category or property be unavailable the given fallback is
     * returned.
     */
    public String[] getArray(String category, String property, String[] fallback) {
        try {
            return get(category, property).split(",");
        } catch (Throwable t) {
            if (fallback == null) {
                log.error("Error parsing '" + property + "' in '" + category + "'", t);
            } else {
                log.warn("Error parsing '" + property + "' in '" + category + "' using default.");
            }
            return fallback;
        }
    }

    /**
     * Checks if the given property in the given category exists.
     */
    public boolean has(String category, String property) {
        return get(category, property) != null;
    }
}
