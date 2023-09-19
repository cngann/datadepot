package com.circron.domain.wrapper;

import java.util.ArrayList;
import java.util.List;

public abstract class XMLWrapper<T> {
	public abstract T get();

	public abstract void set(T value);

	public static <W> XMLWrapper<W> wrap(W obj, Class<? extends XMLWrapper<W>> wrapperClazz) throws InstantiationException, IllegalAccessException {
		XMLWrapper<W> rtn = wrapperClazz.newInstance();
		rtn.set(obj);
		return rtn;
	}

	public static <W> List<XMLWrapper<W>> wrapAll(List<W> objs, Class<? extends XMLWrapper<W>> wrapperClazz) throws InstantiationException, IllegalAccessException {
		List<XMLWrapper<W>> rtnList = new ArrayList<>();
		for (W obj : objs) {
			rtnList.add(wrap(obj, wrapperClazz));
		}
		return rtnList;
	}

	public static <W> W unwrap(XMLWrapper<W> obj) {
		return obj.get();
	}

	public static <W> List<W> unwrapAll(List<? extends XMLWrapper<W>> objs) {
		List<W> rtnList = new ArrayList<>();

		for (XMLWrapper<W> obj : objs) {
			rtnList.add(unwrap(obj));
		}

		return rtnList;
	}
}
