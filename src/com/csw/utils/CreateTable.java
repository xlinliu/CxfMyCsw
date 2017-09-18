package com.csw.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CreateTable {
	public static void main(String[] args) {
		System.out.println("begin");
		Configuration conf = new Configuration().configure();
		SchemaExport export = new SchemaExport(conf);
		export.create(true, true);
		System.out.println("end");
	}
}
