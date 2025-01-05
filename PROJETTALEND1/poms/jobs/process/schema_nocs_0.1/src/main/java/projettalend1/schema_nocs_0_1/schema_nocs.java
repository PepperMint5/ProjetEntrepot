// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package projettalend1.schema_nocs_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: schema_nocs Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class schema_nocs implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "schema_nocs";
	private final String projectName = "PROJETTALEND1";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					schema_nocs.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(schema_nocs.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSchemaComplianceCheck_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_schema_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_schema_nocs = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String tag;

		public String getTag() {
			return this.tag;
		}

		public String note;

		public String getNote() {
			return this.note;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_schema_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_schema_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_schema_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_schema_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_schema_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_schema_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_schema_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_schema_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_schema_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_schema_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",tag=" + tag);
			sb.append(",note=" + note);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_schema_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_schema_nocs = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String tag;

		public String getTag() {
			return this.tag;
		}

		public String note;

		public String getNote() {
			return this.note;
		}

		public String errorCode;

		public String getErrorCode() {
			return this.errorCode;
		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_schema_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_schema_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_schema_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_schema_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_schema_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_schema_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_schema_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_schema_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_schema_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

					this.errorCode = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_schema_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

					this.errorCode = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

				// String

				writeString(this.errorCode, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

				// String

				writeString(this.errorCode, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",tag=" + tag);
			sb.append(",note=" + note);
			sb.append(",errorCode=" + errorCode);
			sb.append(",errorMessage=" + errorMessage);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_schema_nocs = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_schema_nocs = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String tag;

		public String getTag() {
			return this.tag;
		}

		public String note;

		public String getNote() {
			return this.note;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_schema_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_schema_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_schema_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_schema_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_schema_nocs.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_schema_nocs.length == 0) {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_schema_nocs = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_schema_nocs, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_schema_nocs, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_schema_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_schema_nocs) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.tag = readString(dis);

					this.note = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.tag, dos);

				// String

				writeString(this.note, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",tag=" + tag);
			sb.append(",note=" + note);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();
				row3Struct row3 = new row3Struct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = "|";
				java.io.PrintStream consoleOut_tLogRow_1 = null;

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tLogRow_2 begin ] start
				 */

				ok_Hash.put("tLogRow_2", false);
				start_Hash.put("tLogRow_2", System.currentTimeMillis());

				currentComponent = "tLogRow_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tLogRow_2 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_2 = "|";
				java.io.PrintStream consoleOut_tLogRow_2 = null;

				StringBuilder strBuffer_tLogRow_2 = null;
				int nb_line_tLogRow_2 = 0;
///////////////////////    			

				/**
				 * [tLogRow_2 begin ] stop
				 */

				/**
				 * [tSchemaComplianceCheck_1 begin ] start
				 */

				ok_Hash.put("tSchemaComplianceCheck_1", false);
				start_Hash.put("tSchemaComplianceCheck_1", System.currentTimeMillis());

				currentComponent = "tSchemaComplianceCheck_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tSchemaComplianceCheck_1 = 0;

				class RowSetValueUtil_tSchemaComplianceCheck_1 {

					boolean ifPassedThrough = true;
					int errorCodeThrough = 0;
					String errorMessageThrough = "";
					int resultErrorCodeThrough = 0;
					String resultErrorMessageThrough = "";
					String tmpContentThrough = null;

					boolean ifPassed = true;
					int errorCode = 0;
					String errorMessage = "";

					void handleBigdecimalPrecision(String data, int iPrecision, int maxLength) {
						// number of digits before the decimal point(ignoring frontend zeroes)
						int len1 = 0;
						int len2 = 0;
						ifPassed = true;
						errorCode = 0;
						errorMessage = "";
						if (data.startsWith("-")) {
							data = data.substring(1);
						}
						data = org.apache.commons.lang.StringUtils.stripStart(data, "0");

						if (data.indexOf(".") >= 0) {
							len1 = data.indexOf(".");
							data = org.apache.commons.lang.StringUtils.stripEnd(data, "0");
							len2 = data.length() - (len1 + 1);
						} else {
							len1 = data.length();
						}

						if (iPrecision < len2) {
							ifPassed = false;
							errorCode += 8;
							errorMessage += "|precision Non-matches";
						} else if (maxLength < len1 + iPrecision) {
							ifPassed = false;
							errorCode += 8;
							errorMessage += "|invalid Length setting is unsuitable for Precision";
						}
					}

					int handleErrorCode(int errorCode, int resultErrorCode) {
						if (errorCode > 0) {
							if (resultErrorCode > 0) {
								resultErrorCode = 16;
							} else {
								resultErrorCode = errorCode;
							}
						}
						return resultErrorCode;
					}

					String handleErrorMessage(String errorMessage, String resultErrorMessage, String columnLabel) {
						if (errorMessage.length() > 0) {
							if (resultErrorMessage.length() > 0) {
								resultErrorMessage += ";" + errorMessage.replaceFirst("\\|", columnLabel);
							} else {
								resultErrorMessage = errorMessage.replaceFirst("\\|", columnLabel);
							}
						}
						return resultErrorMessage;
					}

					void reset() {
						ifPassedThrough = true;
						errorCodeThrough = 0;
						errorMessageThrough = "";
						resultErrorCodeThrough = 0;
						resultErrorMessageThrough = "";
						tmpContentThrough = null;

						ifPassed = true;
						errorCode = 0;
						errorMessage = "";
					}

					void setRowValue_0(row1Struct row1) {
						try {
							if (row1.code != null && (!"".equals(row1.code))) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.code);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						if (row1.code != null && (!"".equals(row1.code))) {
							if (row1.code.length() > 3) {
								ifPassedThrough = false;
								errorCodeThrough += 8;
								errorMessageThrough += "|exceed max length";
							}
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"code:");
						errorMessageThrough = "";
						try {
							if (row1.country != null && (!"".equals(row1.country))) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.country);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"country:");
						errorMessageThrough = "";
						try {
							if (row1.country_long != null && (!"".equals(row1.country_long))) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.country_long);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"country_long:");
						errorMessageThrough = "";
						try {
							if (row1.tag != null && (!"".equals(row1.tag))) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.tag);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"tag:");
						errorMessageThrough = "";
						try {
							if (row1.note != null && (!"".equals(row1.note))) {
								String tester_tSchemaComplianceCheck_1 = String.valueOf(row1.note);
							}
						} catch (java.lang.Exception e) {
							globalMap.put("tSchemaComplianceCheck_1_ERROR_MESSAGE", e.getMessage());
							ifPassedThrough = false;
							errorCodeThrough += 2;
							errorMessageThrough += "|wrong type";
						}
						if (row1.note != null && (!"".equals(row1.note))) {
							if (row1.note.length() > 2) {
								ifPassedThrough = false;
								errorCodeThrough += 8;
								errorMessageThrough += "|exceed max length";
							}
						}
						resultErrorCodeThrough = handleErrorCode(errorCodeThrough, resultErrorCodeThrough);
						errorCodeThrough = 0;
						resultErrorMessageThrough = handleErrorMessage(errorMessageThrough, resultErrorMessageThrough,
								"note:");
						errorMessageThrough = "";
					}
				}
				RowSetValueUtil_tSchemaComplianceCheck_1 rsvUtil_tSchemaComplianceCheck_1 = new RowSetValueUtil_tSchemaComplianceCheck_1();

				/**
				 * [tSchemaComplianceCheck_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				int footer_tFileInputDelimited_1 = 0;
				int totalLinetFileInputDelimited_1 = 0;
				int limittFileInputDelimited_1 = -1;
				int lastLinetFileInputDelimited_1 = -1;

				char fieldSeparator_tFileInputDelimited_1[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) ",").length() > 0) {
					fieldSeparator_tFileInputDelimited_1 = ((String) ",").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_1[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\n").length() > 0) {
					rowSeparator_tFileInputDelimited_1 = ((String) "\n").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_1 = /** Start field tFileInputDelimited_1:FILENAME */
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/nocs.csv"/**
																															 * End
																															 * field
																															 * tFileInputDelimited_1:FILENAME
																															 */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_1 = null;

				try {

					String[] rowtFileInputDelimited_1 = null;
					int currentLinetFileInputDelimited_1 = 0;
					int outputLinetFileInputDelimited_1 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_1 = 0;
							if (footer_value_tFileInputDelimited_1 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_1,
									fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
						} else {
							csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_1),
									fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
						}

						csvReadertFileInputDelimited_1.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_1[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_1[0] != '\r'))
							csvReadertFileInputDelimited_1.setLineEnd("" + rowSeparator_tFileInputDelimited_1[0]);

						csvReadertFileInputDelimited_1.setQuoteChar('"');

						csvReadertFileInputDelimited_1.setEscapeChar(csvReadertFileInputDelimited_1.getQuoteChar());

						if (footer_tFileInputDelimited_1 > 0) {
							for (totalLinetFileInputDelimited_1 = 0; totalLinetFileInputDelimited_1 < 1; totalLinetFileInputDelimited_1++) {
								csvReadertFileInputDelimited_1.readNext();
							}
							csvReadertFileInputDelimited_1.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_1.readNext()) {

								totalLinetFileInputDelimited_1++;

							}
							int lastLineTemptFileInputDelimited_1 = totalLinetFileInputDelimited_1
									- footer_tFileInputDelimited_1 < 0 ? 0
											: totalLinetFileInputDelimited_1 - footer_tFileInputDelimited_1;
							if (lastLinetFileInputDelimited_1 > 0) {
								lastLinetFileInputDelimited_1 = lastLinetFileInputDelimited_1 < lastLineTemptFileInputDelimited_1
										? lastLinetFileInputDelimited_1
										: lastLineTemptFileInputDelimited_1;
							} else {
								lastLinetFileInputDelimited_1 = lastLineTemptFileInputDelimited_1;
							}

							csvReadertFileInputDelimited_1.close();
							if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_1,
										fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
							} else {
								csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_1),
										fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
							}
							csvReadertFileInputDelimited_1.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_1[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_1[0] != '\r'))
								csvReadertFileInputDelimited_1.setLineEnd("" + rowSeparator_tFileInputDelimited_1[0]);

							csvReadertFileInputDelimited_1.setQuoteChar('"');

							csvReadertFileInputDelimited_1.setEscapeChar(csvReadertFileInputDelimited_1.getQuoteChar());

						}

						if (limittFileInputDelimited_1 != 0) {
							for (currentLinetFileInputDelimited_1 = 0; currentLinetFileInputDelimited_1 < 1; currentLinetFileInputDelimited_1++) {
								csvReadertFileInputDelimited_1.readNext();
							}
						}
						csvReadertFileInputDelimited_1.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					while (limittFileInputDelimited_1 != 0 && csvReadertFileInputDelimited_1 != null
							&& csvReadertFileInputDelimited_1.readNext()) {
						rowstate_tFileInputDelimited_1.reset();

						rowtFileInputDelimited_1 = csvReadertFileInputDelimited_1.getValues();

						currentLinetFileInputDelimited_1++;

						if (lastLinetFileInputDelimited_1 > -1
								&& currentLinetFileInputDelimited_1 > lastLinetFileInputDelimited_1) {
							break;
						}
						outputLinetFileInputDelimited_1++;
						if (limittFileInputDelimited_1 > 0
								&& outputLinetFileInputDelimited_1 > limittFileInputDelimited_1) {
							break;
						}

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							char fieldSeparator_tFileInputDelimited_1_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) ",").length() > 0) {
								fieldSeparator_tFileInputDelimited_1_ListType = ((String) ",").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_1.length == 1 && ("\015").equals(rowtFileInputDelimited_1[0])) {// empty
																														// line
																														// when
																														// row
																														// separator
																														// is
																														// '\n'

								row1.code = null;

								row1.country = null;

								row1.country_long = null;

								row1.tag = null;

								row1.note = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_1 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_1 = 0;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.code = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.code = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 1;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 2;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country_long = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 3;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.tag = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.tag = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 4;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.note = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.note = null;

								}

							}

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {
							row3 = null;

							/**
							 * [tSchemaComplianceCheck_1 main ] start
							 */

							currentComponent = "tSchemaComplianceCheck_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							row2 = null;
							row3 = null;
							rsvUtil_tSchemaComplianceCheck_1.setRowValue_0(row1);
							if (rsvUtil_tSchemaComplianceCheck_1.ifPassedThrough) {
								row2 = new row2Struct();
								row2.code = row1.code;
								row2.country = row1.country;
								row2.country_long = row1.country_long;
								row2.tag = row1.tag;
								row2.note = row1.note;
							}
							if (!rsvUtil_tSchemaComplianceCheck_1.ifPassedThrough) {
								row3 = new row3Struct();
								row3.code = row1.code;
								row3.country = row1.country;
								row3.country_long = row1.country_long;
								row3.tag = row1.tag;
								row3.note = row1.note;
								row3.errorCode = String
										.valueOf(rsvUtil_tSchemaComplianceCheck_1.resultErrorCodeThrough);
								row3.errorMessage = rsvUtil_tSchemaComplianceCheck_1.resultErrorMessageThrough;
							}
							rsvUtil_tSchemaComplianceCheck_1.reset();

							tos_count_tSchemaComplianceCheck_1++;

							/**
							 * [tSchemaComplianceCheck_1 main ] stop
							 */

							/**
							 * [tSchemaComplianceCheck_1 process_data_begin ] start
							 */

							currentComponent = "tSchemaComplianceCheck_1";

							/**
							 * [tSchemaComplianceCheck_1 process_data_begin ] stop
							 */
// Start of branch "row2"
							if (row2 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

///////////////////////		

								strBuffer_tLogRow_1 = new StringBuilder();

								if (row2.code != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.code));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.country != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.country));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.country_long != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.country_long));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.tag != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.tag));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.note != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.note));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_1 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
								}
								consoleOut_tLogRow_1.println(strBuffer_tLogRow_1.toString());
								consoleOut_tLogRow_1.flush();
								nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_1++;

								/**
								 * [tLogRow_1 main ] stop
								 */

								/**
								 * [tLogRow_1 process_data_begin ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "row2"

// Start of branch "row3"
							if (row3 != null) {

								/**
								 * [tLogRow_2 main ] start
								 */

								currentComponent = "tLogRow_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row3"

									);
								}

///////////////////////		

								strBuffer_tLogRow_2 = new StringBuilder();

								if (row3.code != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.code));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.country != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.country));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.country_long != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.country_long));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.tag != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.tag));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.note != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.note));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.errorCode != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.errorCode));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.errorMessage != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.errorMessage));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_2 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_2);
								}
								consoleOut_tLogRow_2.println(strBuffer_tLogRow_2.toString());
								consoleOut_tLogRow_2.flush();
								nb_line_tLogRow_2++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_2++;

								/**
								 * [tLogRow_2 main ] stop
								 */

								/**
								 * [tLogRow_2 process_data_begin ] start
								 */

								currentComponent = "tLogRow_2";

								/**
								 * [tLogRow_2 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_2 process_data_end ] start
								 */

								currentComponent = "tLogRow_2";

								/**
								 * [tLogRow_2 process_data_end ] stop
								 */

							} // End of branch "row3"

							/**
							 * [tSchemaComplianceCheck_1 process_data_end ] start
							 */

							currentComponent = "tSchemaComplianceCheck_1";

							/**
							 * [tSchemaComplianceCheck_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						nb_line_tFileInputDelimited_1++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_1 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_1 != null) {
							csvReadertFileInputDelimited_1.close();
						}
					}
					if (csvReadertFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", nb_line_tFileInputDelimited_1);
					}

				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tSchemaComplianceCheck_1 end ] start
				 */

				currentComponent = "tSchemaComplianceCheck_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tSchemaComplianceCheck_1", true);
				end_Hash.put("tSchemaComplianceCheck_1", System.currentTimeMillis());

				/**
				 * [tSchemaComplianceCheck_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tLogRow_2 end ] start
				 */

				currentComponent = "tLogRow_2";

//////
//////
				globalMap.put("tLogRow_2_NB_LINE", nb_line_tLogRow_2);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tLogRow_2", true);
				end_Hash.put("tLogRow_2", System.currentTimeMillis());

				/**
				 * [tLogRow_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tSchemaComplianceCheck_1 finally ] start
				 */

				currentComponent = "tSchemaComplianceCheck_1";

				/**
				 * [tSchemaComplianceCheck_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				currentComponent = "tLogRow_2";

				/**
				 * [tLogRow_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final schema_nocs schema_nocsClass = new schema_nocs();

		int exitCode = schema_nocsClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = schema_nocs.class.getClassLoader()
					.getResourceAsStream("projettalend1/schema_nocs_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = schema_nocs.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : schema_nocs");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 76832 characters generated by Talend Open Studio for Data Integration on the
 * 3 janvier 2025 à 10:27:39 CET
 ************************************************************************************************/