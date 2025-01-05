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

package projettalend1.doublons_medallists_0_1;

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
 * Job: doublons_medallists Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class doublons_medallists implements TalendJob {

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
	private final String jobName = "doublons_medallists";
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
					doublons_medallists.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(doublons_medallists.this, new Object[] { e, currentComponent, globalMap });
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

	public void tUniqRow_1_error(Exception exception, String errorComponent,
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

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_doublons_medallists = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_doublons_medallists = new byte[0];

		public java.util.Date medal_date;

		public java.util.Date getMedal_date() {
			return this.medal_date;
		}

		public String medal_type;

		public String getMedal_type() {
			return this.medal_type;
		}

		public Float medal_code;

		public Float getMedal_code() {
			return this.medal_code;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String country_code;

		public String getCountry_code() {
			return this.country_code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public String team_gender;

		public String getTeam_gender() {
			return this.team_gender;
		}

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String event;

		public String getEvent() {
			return this.event;
		}

		public String event_type;

		public String getEvent_type() {
			return this.event_type;
		}

		public String url_event;

		public String getUrl_event() {
			return this.url_event;
		}

		public String birth_date;

		public String getBirth_date() {
			return this.birth_date;
		}

		public String code_athlete;

		public String getCode_athlete() {
			return this.code_athlete;
		}

		public String code_team;

		public String getCode_team() {
			return this.code_team;
		}

		public String is_medallist;

		public String getIs_medallist() {
			return this.is_medallist;
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_date = readDate(dis);

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.name = readString(dis);

					this.gender = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality_code = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.team = readString(dis);

					this.team_gender = readString(dis);

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.birth_date = readString(dis);

					this.code_athlete = readString(dis);

					this.code_team = readString(dis);

					this.is_medallist = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_date = readDate(dis);

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.name = readString(dis);

					this.gender = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality_code = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.team = readString(dis);

					this.team_gender = readString(dis);

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.birth_date = readString(dis);

					this.code_athlete = readString(dis);

					this.code_team = readString(dis);

					this.is_medallist = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// String

				writeString(this.name, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.team, dos);

				// String

				writeString(this.team_gender, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.birth_date, dos);

				// String

				writeString(this.code_athlete, dos);

				// String

				writeString(this.code_team, dos);

				// String

				writeString(this.is_medallist, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// String

				writeString(this.name, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.team, dos);

				// String

				writeString(this.team_gender, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.birth_date, dos);

				// String

				writeString(this.code_athlete, dos);

				// String

				writeString(this.code_team, dos);

				// String

				writeString(this.is_medallist, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("medal_date=" + String.valueOf(medal_date));
			sb.append(",medal_type=" + medal_type);
			sb.append(",medal_code=" + String.valueOf(medal_code));
			sb.append(",name=" + name);
			sb.append(",gender=" + gender);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + team_gender);
			sb.append(",discipline=" + discipline);
			sb.append(",event=" + event);
			sb.append(",event_type=" + event_type);
			sb.append(",url_event=" + url_event);
			sb.append(",birth_date=" + birth_date);
			sb.append(",code_athlete=" + code_athlete);
			sb.append(",code_team=" + code_team);
			sb.append(",is_medallist=" + is_medallist);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_doublons_medallists = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_doublons_medallists = new byte[0];

		public java.util.Date medal_date;

		public java.util.Date getMedal_date() {
			return this.medal_date;
		}

		public String medal_type;

		public String getMedal_type() {
			return this.medal_type;
		}

		public Float medal_code;

		public Float getMedal_code() {
			return this.medal_code;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String country_code;

		public String getCountry_code() {
			return this.country_code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public String team_gender;

		public String getTeam_gender() {
			return this.team_gender;
		}

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String event;

		public String getEvent() {
			return this.event;
		}

		public String event_type;

		public String getEvent_type() {
			return this.event_type;
		}

		public String url_event;

		public String getUrl_event() {
			return this.url_event;
		}

		public String birth_date;

		public String getBirth_date() {
			return this.birth_date;
		}

		public String code_athlete;

		public String getCode_athlete() {
			return this.code_athlete;
		}

		public String code_team;

		public String getCode_team() {
			return this.code_team;
		}

		public String is_medallist;

		public String getIs_medallist() {
			return this.is_medallist;
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_date = readDate(dis);

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.name = readString(dis);

					this.gender = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality_code = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.team = readString(dis);

					this.team_gender = readString(dis);

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.birth_date = readString(dis);

					this.code_athlete = readString(dis);

					this.code_team = readString(dis);

					this.is_medallist = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_date = readDate(dis);

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.name = readString(dis);

					this.gender = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality_code = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.team = readString(dis);

					this.team_gender = readString(dis);

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.birth_date = readString(dis);

					this.code_athlete = readString(dis);

					this.code_team = readString(dis);

					this.is_medallist = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// String

				writeString(this.name, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.team, dos);

				// String

				writeString(this.team_gender, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.birth_date, dos);

				// String

				writeString(this.code_athlete, dos);

				// String

				writeString(this.code_team, dos);

				// String

				writeString(this.is_medallist, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// String

				writeString(this.name, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.team, dos);

				// String

				writeString(this.team_gender, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.birth_date, dos);

				// String

				writeString(this.code_athlete, dos);

				// String

				writeString(this.code_team, dos);

				// String

				writeString(this.is_medallist, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("medal_date=" + String.valueOf(medal_date));
			sb.append(",medal_type=" + medal_type);
			sb.append(",medal_code=" + String.valueOf(medal_code));
			sb.append(",name=" + name);
			sb.append(",gender=" + gender);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + team_gender);
			sb.append(",discipline=" + discipline);
			sb.append(",event=" + event);
			sb.append(",event_type=" + event_type);
			sb.append(",url_event=" + url_event);
			sb.append(",birth_date=" + birth_date);
			sb.append(",code_athlete=" + code_athlete);
			sb.append(",code_team=" + code_team);
			sb.append(",is_medallist=" + is_medallist);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_doublons_medallists = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_doublons_medallists = new byte[0];

		public java.util.Date medal_date;

		public java.util.Date getMedal_date() {
			return this.medal_date;
		}

		public String medal_type;

		public String getMedal_type() {
			return this.medal_type;
		}

		public Float medal_code;

		public Float getMedal_code() {
			return this.medal_code;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String country_code;

		public String getCountry_code() {
			return this.country_code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public String team_gender;

		public String getTeam_gender() {
			return this.team_gender;
		}

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String event;

		public String getEvent() {
			return this.event;
		}

		public String event_type;

		public String getEvent_type() {
			return this.event_type;
		}

		public String url_event;

		public String getUrl_event() {
			return this.url_event;
		}

		public String birth_date;

		public String getBirth_date() {
			return this.birth_date;
		}

		public String code_athlete;

		public String getCode_athlete() {
			return this.code_athlete;
		}

		public String code_team;

		public String getCode_team() {
			return this.code_team;
		}

		public String is_medallist;

		public String getIs_medallist() {
			return this.is_medallist;
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_date = readDate(dis);

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.name = readString(dis);

					this.gender = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality_code = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.team = readString(dis);

					this.team_gender = readString(dis);

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.birth_date = readString(dis);

					this.code_athlete = readString(dis);

					this.code_team = readString(dis);

					this.is_medallist = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_date = readDate(dis);

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.name = readString(dis);

					this.gender = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality_code = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.team = readString(dis);

					this.team_gender = readString(dis);

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.birth_date = readString(dis);

					this.code_athlete = readString(dis);

					this.code_team = readString(dis);

					this.is_medallist = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// String

				writeString(this.name, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.team, dos);

				// String

				writeString(this.team_gender, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.birth_date, dos);

				// String

				writeString(this.code_athlete, dos);

				// String

				writeString(this.code_team, dos);

				// String

				writeString(this.is_medallist, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// String

				writeString(this.name, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.team, dos);

				// String

				writeString(this.team_gender, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.birth_date, dos);

				// String

				writeString(this.code_athlete, dos);

				// String

				writeString(this.code_team, dos);

				// String

				writeString(this.is_medallist, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("medal_date=" + String.valueOf(medal_date));
			sb.append(",medal_type=" + medal_type);
			sb.append(",medal_code=" + String.valueOf(medal_code));
			sb.append(",name=" + name);
			sb.append(",gender=" + gender);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + team_gender);
			sb.append(",discipline=" + discipline);
			sb.append(",event=" + event);
			sb.append(",event_type=" + event_type);
			sb.append(",url_event=" + url_event);
			sb.append(",birth_date=" + birth_date);
			sb.append(",code_athlete=" + code_athlete);
			sb.append(",code_team=" + code_team);
			sb.append(",is_medallist=" + is_medallist);
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
				 * [tUniqRow_1 begin ] start
				 */

				ok_Hash.put("tUniqRow_1", false);
				start_Hash.put("tUniqRow_1", System.currentTimeMillis());

				currentComponent = "tUniqRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tUniqRow_1 = 0;

				class KeyStruct_tUniqRow_1 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					java.util.Date medal_date;
					String medal_type;
					Float medal_code;
					String name;
					String gender;
					String country_code;
					String country;
					String country_long;
					String nationality_code;
					String nationality;
					String nationality_long;
					String team;
					String team_gender;
					String discipline;
					String event;
					String event_type;
					String url_event;
					String birth_date;
					String code_athlete;
					String code_team;
					String is_medallist;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.medal_date == null) ? 0 : this.medal_date.hashCode());

							result = prime * result + ((this.medal_type == null) ? 0 : this.medal_type.hashCode());

							result = prime * result + ((this.medal_code == null) ? 0 : this.medal_code.hashCode());

							result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());

							result = prime * result + ((this.gender == null) ? 0 : this.gender.hashCode());

							result = prime * result + ((this.country_code == null) ? 0 : this.country_code.hashCode());

							result = prime * result + ((this.country == null) ? 0 : this.country.hashCode());

							result = prime * result + ((this.country_long == null) ? 0 : this.country_long.hashCode());

							result = prime * result
									+ ((this.nationality_code == null) ? 0 : this.nationality_code.hashCode());

							result = prime * result + ((this.nationality == null) ? 0 : this.nationality.hashCode());

							result = prime * result
									+ ((this.nationality_long == null) ? 0 : this.nationality_long.hashCode());

							result = prime * result + ((this.team == null) ? 0 : this.team.hashCode());

							result = prime * result + ((this.team_gender == null) ? 0 : this.team_gender.hashCode());

							result = prime * result + ((this.discipline == null) ? 0 : this.discipline.hashCode());

							result = prime * result + ((this.event == null) ? 0 : this.event.hashCode());

							result = prime * result + ((this.event_type == null) ? 0 : this.event_type.hashCode());

							result = prime * result + ((this.url_event == null) ? 0 : this.url_event.hashCode());

							result = prime * result + ((this.birth_date == null) ? 0 : this.birth_date.hashCode());

							result = prime * result + ((this.code_athlete == null) ? 0 : this.code_athlete.hashCode());

							result = prime * result + ((this.code_team == null) ? 0 : this.code_team.hashCode());

							result = prime * result + ((this.is_medallist == null) ? 0 : this.is_medallist.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_1 other = (KeyStruct_tUniqRow_1) obj;

						if (this.medal_date == null) {
							if (other.medal_date != null)
								return false;

						} else if (!this.medal_date.equals(other.medal_date))

							return false;

						if (this.medal_type == null) {
							if (other.medal_type != null)
								return false;

						} else if (!this.medal_type.equals(other.medal_type))

							return false;

						if (this.medal_code == null) {
							if (other.medal_code != null)
								return false;

						} else if (!this.medal_code.equals(other.medal_code))

							return false;

						if (this.name == null) {
							if (other.name != null)
								return false;

						} else if (!this.name.equals(other.name))

							return false;

						if (this.gender == null) {
							if (other.gender != null)
								return false;

						} else if (!this.gender.equals(other.gender))

							return false;

						if (this.country_code == null) {
							if (other.country_code != null)
								return false;

						} else if (!this.country_code.equals(other.country_code))

							return false;

						if (this.country == null) {
							if (other.country != null)
								return false;

						} else if (!this.country.equals(other.country))

							return false;

						if (this.country_long == null) {
							if (other.country_long != null)
								return false;

						} else if (!this.country_long.equals(other.country_long))

							return false;

						if (this.nationality_code == null) {
							if (other.nationality_code != null)
								return false;

						} else if (!this.nationality_code.equals(other.nationality_code))

							return false;

						if (this.nationality == null) {
							if (other.nationality != null)
								return false;

						} else if (!this.nationality.equals(other.nationality))

							return false;

						if (this.nationality_long == null) {
							if (other.nationality_long != null)
								return false;

						} else if (!this.nationality_long.equals(other.nationality_long))

							return false;

						if (this.team == null) {
							if (other.team != null)
								return false;

						} else if (!this.team.equals(other.team))

							return false;

						if (this.team_gender == null) {
							if (other.team_gender != null)
								return false;

						} else if (!this.team_gender.equals(other.team_gender))

							return false;

						if (this.discipline == null) {
							if (other.discipline != null)
								return false;

						} else if (!this.discipline.equals(other.discipline))

							return false;

						if (this.event == null) {
							if (other.event != null)
								return false;

						} else if (!this.event.equals(other.event))

							return false;

						if (this.event_type == null) {
							if (other.event_type != null)
								return false;

						} else if (!this.event_type.equals(other.event_type))

							return false;

						if (this.url_event == null) {
							if (other.url_event != null)
								return false;

						} else if (!this.url_event.equals(other.url_event))

							return false;

						if (this.birth_date == null) {
							if (other.birth_date != null)
								return false;

						} else if (!this.birth_date.equals(other.birth_date))

							return false;

						if (this.code_athlete == null) {
							if (other.code_athlete != null)
								return false;

						} else if (!this.code_athlete.equals(other.code_athlete))

							return false;

						if (this.code_team == null) {
							if (other.code_team != null)
								return false;

						} else if (!this.code_team.equals(other.code_team))

							return false;

						if (this.is_medallist == null) {
							if (other.is_medallist != null)
								return false;

						} else if (!this.is_medallist.equals(other.is_medallist))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_1 = 0;
				int nb_duplicates_tUniqRow_1 = 0;
				KeyStruct_tUniqRow_1 finder_tUniqRow_1 = new KeyStruct_tUniqRow_1();
				java.util.Set<KeyStruct_tUniqRow_1> keystUniqRow_1 = new java.util.HashSet<KeyStruct_tUniqRow_1>();

				/**
				 * [tUniqRow_1 begin ] stop
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
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/medallists.csv"/**
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

								row1.medal_date = null;

								row1.medal_type = null;

								row1.medal_code = null;

								row1.name = null;

								row1.gender = null;

								row1.country_code = null;

								row1.country = null;

								row1.country_long = null;

								row1.nationality_code = null;

								row1.nationality = null;

								row1.nationality_long = null;

								row1.team = null;

								row1.team_gender = null;

								row1.discipline = null;

								row1.event = null;

								row1.event_type = null;

								row1.url_event = null;

								row1.birth_date = null;

								row1.code_athlete = null;

								row1.code_team = null;

								row1.is_medallist = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_1 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_1 = 0;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row1.medal_date = ParserUtils.parseTo_Date(
													rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
													"yyyy-mm-dd");

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"medal_date", "row1",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row1.medal_date = null;

									}

								} else {

									row1.medal_date = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 1;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.medal_type = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.medal_type = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 2;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row1.medal_code = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]);

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"medal_code", "row1",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row1.medal_code = null;

									}

								} else {

									row1.medal_code = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 3;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.name = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.name = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 4;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.gender = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.gender = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 5;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country_code = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country_code = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 6;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 7;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country_long = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 8;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.nationality_code = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.nationality_code = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 9;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.nationality = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.nationality = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 10;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.nationality_long = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.nationality_long = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 11;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.team = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.team = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 12;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.team_gender = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.team_gender = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 13;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.discipline = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.discipline = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 14;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.event = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.event = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 15;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.event_type = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.event_type = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 16;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.url_event = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.url_event = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 17;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.birth_date = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.birth_date = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 18;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.code_athlete = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.code_athlete = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 19;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.code_team = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.code_team = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 20;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.is_medallist = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.is_medallist = null;

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

							/**
							 * [tUniqRow_1 main ] start
							 */

							currentComponent = "tUniqRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							row3 = null;
							row2 = null;
							finder_tUniqRow_1.medal_date = row1.medal_date;
							if (row1.medal_type == null) {
								finder_tUniqRow_1.medal_type = null;
							} else {
								finder_tUniqRow_1.medal_type = row1.medal_type.toLowerCase();
							}
							finder_tUniqRow_1.medal_code = row1.medal_code;
							if (row1.name == null) {
								finder_tUniqRow_1.name = null;
							} else {
								finder_tUniqRow_1.name = row1.name.toLowerCase();
							}
							if (row1.gender == null) {
								finder_tUniqRow_1.gender = null;
							} else {
								finder_tUniqRow_1.gender = row1.gender.toLowerCase();
							}
							if (row1.country_code == null) {
								finder_tUniqRow_1.country_code = null;
							} else {
								finder_tUniqRow_1.country_code = row1.country_code.toLowerCase();
							}
							if (row1.country == null) {
								finder_tUniqRow_1.country = null;
							} else {
								finder_tUniqRow_1.country = row1.country.toLowerCase();
							}
							if (row1.country_long == null) {
								finder_tUniqRow_1.country_long = null;
							} else {
								finder_tUniqRow_1.country_long = row1.country_long.toLowerCase();
							}
							if (row1.nationality_code == null) {
								finder_tUniqRow_1.nationality_code = null;
							} else {
								finder_tUniqRow_1.nationality_code = row1.nationality_code.toLowerCase();
							}
							if (row1.nationality == null) {
								finder_tUniqRow_1.nationality = null;
							} else {
								finder_tUniqRow_1.nationality = row1.nationality.toLowerCase();
							}
							if (row1.nationality_long == null) {
								finder_tUniqRow_1.nationality_long = null;
							} else {
								finder_tUniqRow_1.nationality_long = row1.nationality_long.toLowerCase();
							}
							if (row1.team == null) {
								finder_tUniqRow_1.team = null;
							} else {
								finder_tUniqRow_1.team = row1.team.toLowerCase();
							}
							if (row1.team_gender == null) {
								finder_tUniqRow_1.team_gender = null;
							} else {
								finder_tUniqRow_1.team_gender = row1.team_gender.toLowerCase();
							}
							if (row1.discipline == null) {
								finder_tUniqRow_1.discipline = null;
							} else {
								finder_tUniqRow_1.discipline = row1.discipline.toLowerCase();
							}
							if (row1.event == null) {
								finder_tUniqRow_1.event = null;
							} else {
								finder_tUniqRow_1.event = row1.event.toLowerCase();
							}
							if (row1.event_type == null) {
								finder_tUniqRow_1.event_type = null;
							} else {
								finder_tUniqRow_1.event_type = row1.event_type.toLowerCase();
							}
							if (row1.url_event == null) {
								finder_tUniqRow_1.url_event = null;
							} else {
								finder_tUniqRow_1.url_event = row1.url_event.toLowerCase();
							}
							if (row1.birth_date == null) {
								finder_tUniqRow_1.birth_date = null;
							} else {
								finder_tUniqRow_1.birth_date = row1.birth_date.toLowerCase();
							}
							if (row1.code_athlete == null) {
								finder_tUniqRow_1.code_athlete = null;
							} else {
								finder_tUniqRow_1.code_athlete = row1.code_athlete.toLowerCase();
							}
							if (row1.code_team == null) {
								finder_tUniqRow_1.code_team = null;
							} else {
								finder_tUniqRow_1.code_team = row1.code_team.toLowerCase();
							}
							if (row1.is_medallist == null) {
								finder_tUniqRow_1.is_medallist = null;
							} else {
								finder_tUniqRow_1.is_medallist = row1.is_medallist.toLowerCase();
							}
							finder_tUniqRow_1.hashCodeDirty = true;
							if (!keystUniqRow_1.contains(finder_tUniqRow_1)) {
								KeyStruct_tUniqRow_1 new_tUniqRow_1 = new KeyStruct_tUniqRow_1();

								new_tUniqRow_1.medal_date = row1.medal_date;
								if (row1.medal_type == null) {
									new_tUniqRow_1.medal_type = null;
								} else {
									new_tUniqRow_1.medal_type = row1.medal_type.toLowerCase();
								}
								new_tUniqRow_1.medal_code = row1.medal_code;
								if (row1.name == null) {
									new_tUniqRow_1.name = null;
								} else {
									new_tUniqRow_1.name = row1.name.toLowerCase();
								}
								if (row1.gender == null) {
									new_tUniqRow_1.gender = null;
								} else {
									new_tUniqRow_1.gender = row1.gender.toLowerCase();
								}
								if (row1.country_code == null) {
									new_tUniqRow_1.country_code = null;
								} else {
									new_tUniqRow_1.country_code = row1.country_code.toLowerCase();
								}
								if (row1.country == null) {
									new_tUniqRow_1.country = null;
								} else {
									new_tUniqRow_1.country = row1.country.toLowerCase();
								}
								if (row1.country_long == null) {
									new_tUniqRow_1.country_long = null;
								} else {
									new_tUniqRow_1.country_long = row1.country_long.toLowerCase();
								}
								if (row1.nationality_code == null) {
									new_tUniqRow_1.nationality_code = null;
								} else {
									new_tUniqRow_1.nationality_code = row1.nationality_code.toLowerCase();
								}
								if (row1.nationality == null) {
									new_tUniqRow_1.nationality = null;
								} else {
									new_tUniqRow_1.nationality = row1.nationality.toLowerCase();
								}
								if (row1.nationality_long == null) {
									new_tUniqRow_1.nationality_long = null;
								} else {
									new_tUniqRow_1.nationality_long = row1.nationality_long.toLowerCase();
								}
								if (row1.team == null) {
									new_tUniqRow_1.team = null;
								} else {
									new_tUniqRow_1.team = row1.team.toLowerCase();
								}
								if (row1.team_gender == null) {
									new_tUniqRow_1.team_gender = null;
								} else {
									new_tUniqRow_1.team_gender = row1.team_gender.toLowerCase();
								}
								if (row1.discipline == null) {
									new_tUniqRow_1.discipline = null;
								} else {
									new_tUniqRow_1.discipline = row1.discipline.toLowerCase();
								}
								if (row1.event == null) {
									new_tUniqRow_1.event = null;
								} else {
									new_tUniqRow_1.event = row1.event.toLowerCase();
								}
								if (row1.event_type == null) {
									new_tUniqRow_1.event_type = null;
								} else {
									new_tUniqRow_1.event_type = row1.event_type.toLowerCase();
								}
								if (row1.url_event == null) {
									new_tUniqRow_1.url_event = null;
								} else {
									new_tUniqRow_1.url_event = row1.url_event.toLowerCase();
								}
								if (row1.birth_date == null) {
									new_tUniqRow_1.birth_date = null;
								} else {
									new_tUniqRow_1.birth_date = row1.birth_date.toLowerCase();
								}
								if (row1.code_athlete == null) {
									new_tUniqRow_1.code_athlete = null;
								} else {
									new_tUniqRow_1.code_athlete = row1.code_athlete.toLowerCase();
								}
								if (row1.code_team == null) {
									new_tUniqRow_1.code_team = null;
								} else {
									new_tUniqRow_1.code_team = row1.code_team.toLowerCase();
								}
								if (row1.is_medallist == null) {
									new_tUniqRow_1.is_medallist = null;
								} else {
									new_tUniqRow_1.is_medallist = row1.is_medallist.toLowerCase();
								}

								keystUniqRow_1.add(new_tUniqRow_1);
								if (row2 == null) {

									row2 = new row2Struct();
								}
								row2.medal_date = row1.medal_date;
								row2.medal_type = row1.medal_type;
								row2.medal_code = row1.medal_code;
								row2.name = row1.name;
								row2.gender = row1.gender;
								row2.country_code = row1.country_code;
								row2.country = row1.country;
								row2.country_long = row1.country_long;
								row2.nationality_code = row1.nationality_code;
								row2.nationality = row1.nationality;
								row2.nationality_long = row1.nationality_long;
								row2.team = row1.team;
								row2.team_gender = row1.team_gender;
								row2.discipline = row1.discipline;
								row2.event = row1.event;
								row2.event_type = row1.event_type;
								row2.url_event = row1.url_event;
								row2.birth_date = row1.birth_date;
								row2.code_athlete = row1.code_athlete;
								row2.code_team = row1.code_team;
								row2.is_medallist = row1.is_medallist;
								nb_uniques_tUniqRow_1++;
							} else {
								if (row3 == null) {

									row3 = new row3Struct();
								}
								row3.medal_date = row1.medal_date;
								row3.medal_type = row1.medal_type;
								row3.medal_code = row1.medal_code;
								row3.name = row1.name;
								row3.gender = row1.gender;
								row3.country_code = row1.country_code;
								row3.country = row1.country;
								row3.country_long = row1.country_long;
								row3.nationality_code = row1.nationality_code;
								row3.nationality = row1.nationality;
								row3.nationality_long = row1.nationality_long;
								row3.team = row1.team;
								row3.team_gender = row1.team_gender;
								row3.discipline = row1.discipline;
								row3.event = row1.event;
								row3.event_type = row1.event_type;
								row3.url_event = row1.url_event;
								row3.birth_date = row1.birth_date;
								row3.code_athlete = row1.code_athlete;
								row3.code_team = row1.code_team;
								row3.is_medallist = row1.is_medallist;
								nb_duplicates_tUniqRow_1++;
							}

							tos_count_tUniqRow_1++;

							/**
							 * [tUniqRow_1 main ] stop
							 */

							/**
							 * [tUniqRow_1 process_data_begin ] start
							 */

							currentComponent = "tUniqRow_1";

							/**
							 * [tUniqRow_1 process_data_begin ] stop
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

								if (row2.medal_date != null) { //

									strBuffer_tLogRow_1
											.append(FormatterUtils.format_Date(row2.medal_date, "yyyy-mm-dd"));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.medal_type != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.medal_type));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.medal_code != null) { //

									strBuffer_tLogRow_1.append(FormatterUtils.formatUnwithE(row2.medal_code));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.name != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.name));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.gender != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.gender));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.country_code != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.country_code));

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

								if (row2.nationality_code != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.nationality_code));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.nationality != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.nationality));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.nationality_long != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.nationality_long));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.team != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.team));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.team_gender != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.team_gender));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.discipline != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.discipline));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.event != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.event));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.event_type != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.event_type));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.url_event != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.url_event));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.birth_date != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.birth_date));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.code_athlete != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.code_athlete));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.code_team != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.code_team));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.is_medallist != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.is_medallist));

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

								if (row3.medal_date != null) { //

									strBuffer_tLogRow_2
											.append(FormatterUtils.format_Date(row3.medal_date, "yyyy-mm-dd"));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.medal_type != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.medal_type));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.medal_code != null) { //

									strBuffer_tLogRow_2.append(FormatterUtils.formatUnwithE(row3.medal_code));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.name != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.name));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.gender != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.gender));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.country_code != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.country_code));

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

								if (row3.nationality_code != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.nationality_code));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.nationality != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.nationality));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.nationality_long != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.nationality_long));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.team != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.team));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.team_gender != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.team_gender));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.discipline != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.discipline));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.event != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.event));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.event_type != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.event_type));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.url_event != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.url_event));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.birth_date != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.birth_date));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.code_athlete != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.code_athlete));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.code_team != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.code_team));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.is_medallist != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.is_medallist));

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
							 * [tUniqRow_1 process_data_end ] start
							 */

							currentComponent = "tUniqRow_1";

							/**
							 * [tUniqRow_1 process_data_end ] stop
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
				 * [tUniqRow_1 end ] start
				 */

				currentComponent = "tUniqRow_1";

				globalMap.put("tUniqRow_1_NB_UNIQUES", nb_uniques_tUniqRow_1);
				globalMap.put("tUniqRow_1_NB_DUPLICATES", nb_duplicates_tUniqRow_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tUniqRow_1", true);
				end_Hash.put("tUniqRow_1", System.currentTimeMillis());

				/**
				 * [tUniqRow_1 end ] stop
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
				 * [tUniqRow_1 finally ] start
				 */

				currentComponent = "tUniqRow_1";

				/**
				 * [tUniqRow_1 finally ] stop
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

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_doublons_medallists = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_doublons_medallists = new byte[0];

		public String medal_type;

		public String getMedal_type() {
			return this.medal_type;
		}

		public Float medal_code;

		public Float getMedal_code() {
			return this.medal_code;
		}

		public java.util.Date medal_date;

		public java.util.Date getMedal_date() {
			return this.medal_date;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public Character gender;

		public Character getGender() {
			return this.gender;
		}

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String event;

		public String getEvent() {
			return this.event;
		}

		public String event_type;

		public String getEvent_type() {
			return this.event_type;
		}

		public String url_event;

		public String getUrl_event() {
			return this.url_event;
		}

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country_code;

		public String getCountry_code() {
			return this.country_code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.medal_date = readDate(dis);

					this.name = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.gender = null;
					} else {
						this.gender = dis.readChar();
					}

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.code = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.medal_date = readDate(dis);

					this.name = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.gender = null;
					} else {
						this.gender = dis.readChar();
					}

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.code = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.name, dos);

				// Character

				if (this.gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.gender);
				}

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.name, dos);

				// Character

				if (this.gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.gender);
				}

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("medal_type=" + medal_type);
			sb.append(",medal_code=" + String.valueOf(medal_code));
			sb.append(",medal_date=" + String.valueOf(medal_date));
			sb.append(",name=" + name);
			sb.append(",gender=" + String.valueOf(gender));
			sb.append(",discipline=" + discipline);
			sb.append(",event=" + event);
			sb.append(",event_type=" + event_type);
			sb.append(",url_event=" + url_event);
			sb.append(",code=" + code);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

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

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_doublons_medallists = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_doublons_medallists = new byte[0];

		public String medal_type;

		public String getMedal_type() {
			return this.medal_type;
		}

		public Float medal_code;

		public Float getMedal_code() {
			return this.medal_code;
		}

		public java.util.Date medal_date;

		public java.util.Date getMedal_date() {
			return this.medal_date;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public Character gender;

		public Character getGender() {
			return this.gender;
		}

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String event;

		public String getEvent() {
			return this.event;
		}

		public String event_type;

		public String getEvent_type() {
			return this.event_type;
		}

		public String url_event;

		public String getUrl_event() {
			return this.url_event;
		}

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country_code;

		public String getCountry_code() {
			return this.country_code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.medal_date = readDate(dis);

					this.name = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.gender = null;
					} else {
						this.gender = dis.readChar();
					}

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.code = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.medal_date = readDate(dis);

					this.name = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.gender = null;
					} else {
						this.gender = dis.readChar();
					}

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.code = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.name, dos);

				// Character

				if (this.gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.gender);
				}

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.name, dos);

				// Character

				if (this.gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.gender);
				}

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("medal_type=" + medal_type);
			sb.append(",medal_code=" + String.valueOf(medal_code));
			sb.append(",medal_date=" + String.valueOf(medal_date));
			sb.append(",name=" + name);
			sb.append(",gender=" + String.valueOf(gender));
			sb.append(",discipline=" + discipline);
			sb.append(",event=" + event);
			sb.append(",event_type=" + event_type);
			sb.append(",url_event=" + url_event);
			sb.append(",code=" + code);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

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

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_doublons_medallists = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_doublons_medallists = new byte[0];

		public String medal_type;

		public String getMedal_type() {
			return this.medal_type;
		}

		public Float medal_code;

		public Float getMedal_code() {
			return this.medal_code;
		}

		public java.util.Date medal_date;

		public java.util.Date getMedal_date() {
			return this.medal_date;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public Character gender;

		public Character getGender() {
			return this.gender;
		}

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String event;

		public String getEvent() {
			return this.event;
		}

		public String event_type;

		public String getEvent_type() {
			return this.event_type;
		}

		public String url_event;

		public String getUrl_event() {
			return this.url_event;
		}

		public String code;

		public String getCode() {
			return this.code;
		}

		public String country_code;

		public String getCountry_code() {
			return this.country_code;
		}

		public String country;

		public String getCountry() {
			return this.country;
		}

		public String country_long;

		public String getCountry_long() {
			return this.country_long;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_doublons_medallists.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_doublons_medallists.length == 0) {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_doublons_medallists = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_doublons_medallists, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.medal_date = readDate(dis);

					this.name = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.gender = null;
					} else {
						this.gender = dis.readChar();
					}

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.code = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_doublons_medallists) {

				try {

					int length = 0;

					this.medal_type = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.medal_code = null;
					} else {
						this.medal_code = dis.readFloat();
					}

					this.medal_date = readDate(dis);

					this.name = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.gender = null;
					} else {
						this.gender = dis.readChar();
					}

					this.discipline = readString(dis);

					this.event = readString(dis);

					this.event_type = readString(dis);

					this.url_event = readString(dis);

					this.code = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.name, dos);

				// Character

				if (this.gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.gender);
				}

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.medal_type, dos);

				// Float

				if (this.medal_code == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.medal_code);
				}

				// java.util.Date

				writeDate(this.medal_date, dos);

				// String

				writeString(this.name, dos);

				// Character

				if (this.gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.gender);
				}

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.event, dos);

				// String

				writeString(this.event_type, dos);

				// String

				writeString(this.url_event, dos);

				// String

				writeString(this.code, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("medal_type=" + medal_type);
			sb.append(",medal_code=" + String.valueOf(medal_code));
			sb.append(",medal_date=" + String.valueOf(medal_date));
			sb.append(",name=" + name);
			sb.append(",gender=" + String.valueOf(gender));
			sb.append(",discipline=" + discipline);
			sb.append(",event=" + event);
			sb.append(",event_type=" + event_type);
			sb.append(",url_event=" + url_event);
			sb.append(",code=" + code);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

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

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

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

				row4Struct row4 = new row4Struct();
				row5Struct row5 = new row5Struct();
				row6Struct row6 = new row6Struct();

				/**
				 * [tLogRow_3 begin ] start
				 */

				ok_Hash.put("tLogRow_3", false);
				start_Hash.put("tLogRow_3", System.currentTimeMillis());

				currentComponent = "tLogRow_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tLogRow_3 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_3 = "|";
				java.io.PrintStream consoleOut_tLogRow_3 = null;

				StringBuilder strBuffer_tLogRow_3 = null;
				int nb_line_tLogRow_3 = 0;
///////////////////////    			

				/**
				 * [tLogRow_3 begin ] stop
				 */

				/**
				 * [tLogRow_4 begin ] start
				 */

				ok_Hash.put("tLogRow_4", false);
				start_Hash.put("tLogRow_4", System.currentTimeMillis());

				currentComponent = "tLogRow_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tLogRow_4 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_4 = "|";
				java.io.PrintStream consoleOut_tLogRow_4 = null;

				StringBuilder strBuffer_tLogRow_4 = null;
				int nb_line_tLogRow_4 = 0;
///////////////////////    			

				/**
				 * [tLogRow_4 begin ] stop
				 */

				/**
				 * [tUniqRow_2 begin ] start
				 */

				ok_Hash.put("tUniqRow_2", false);
				start_Hash.put("tUniqRow_2", System.currentTimeMillis());

				currentComponent = "tUniqRow_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tUniqRow_2 = 0;

				class KeyStruct_tUniqRow_2 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String medal_type;
					Float medal_code;
					java.util.Date medal_date;
					String name;
					Character gender;
					String discipline;
					String event;
					String event_type;
					String url_event;
					String code;
					String country_code;
					String country;
					String country_long;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.medal_type == null) ? 0 : this.medal_type.hashCode());

							result = prime * result + ((this.medal_code == null) ? 0 : this.medal_code.hashCode());

							result = prime * result + ((this.medal_date == null) ? 0 : this.medal_date.hashCode());

							result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());

							result = prime * result + ((this.gender == null) ? 0 : this.gender.hashCode());

							result = prime * result + ((this.discipline == null) ? 0 : this.discipline.hashCode());

							result = prime * result + ((this.event == null) ? 0 : this.event.hashCode());

							result = prime * result + ((this.event_type == null) ? 0 : this.event_type.hashCode());

							result = prime * result + ((this.url_event == null) ? 0 : this.url_event.hashCode());

							result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());

							result = prime * result + ((this.country_code == null) ? 0 : this.country_code.hashCode());

							result = prime * result + ((this.country == null) ? 0 : this.country.hashCode());

							result = prime * result + ((this.country_long == null) ? 0 : this.country_long.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_2 other = (KeyStruct_tUniqRow_2) obj;

						if (this.medal_type == null) {
							if (other.medal_type != null)
								return false;

						} else if (!this.medal_type.equals(other.medal_type))

							return false;

						if (this.medal_code == null) {
							if (other.medal_code != null)
								return false;

						} else if (!this.medal_code.equals(other.medal_code))

							return false;

						if (this.medal_date == null) {
							if (other.medal_date != null)
								return false;

						} else if (!this.medal_date.equals(other.medal_date))

							return false;

						if (this.name == null) {
							if (other.name != null)
								return false;

						} else if (!this.name.equals(other.name))

							return false;

						if (this.gender == null) {
							if (other.gender != null)
								return false;

						} else if (!this.gender.equals(other.gender))

							return false;

						if (this.discipline == null) {
							if (other.discipline != null)
								return false;

						} else if (!this.discipline.equals(other.discipline))

							return false;

						if (this.event == null) {
							if (other.event != null)
								return false;

						} else if (!this.event.equals(other.event))

							return false;

						if (this.event_type == null) {
							if (other.event_type != null)
								return false;

						} else if (!this.event_type.equals(other.event_type))

							return false;

						if (this.url_event == null) {
							if (other.url_event != null)
								return false;

						} else if (!this.url_event.equals(other.url_event))

							return false;

						if (this.code == null) {
							if (other.code != null)
								return false;

						} else if (!this.code.equals(other.code))

							return false;

						if (this.country_code == null) {
							if (other.country_code != null)
								return false;

						} else if (!this.country_code.equals(other.country_code))

							return false;

						if (this.country == null) {
							if (other.country != null)
								return false;

						} else if (!this.country.equals(other.country))

							return false;

						if (this.country_long == null) {
							if (other.country_long != null)
								return false;

						} else if (!this.country_long.equals(other.country_long))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_2 = 0;
				int nb_duplicates_tUniqRow_2 = 0;
				KeyStruct_tUniqRow_2 finder_tUniqRow_2 = new KeyStruct_tUniqRow_2();
				java.util.Set<KeyStruct_tUniqRow_2> keystUniqRow_2 = new java.util.HashSet<KeyStruct_tUniqRow_2>();

				/**
				 * [tUniqRow_2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				int footer_tFileInputDelimited_2 = 0;
				int totalLinetFileInputDelimited_2 = 0;
				int limittFileInputDelimited_2 = -1;
				int lastLinetFileInputDelimited_2 = -1;

				char fieldSeparator_tFileInputDelimited_2[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) ",").length() > 0) {
					fieldSeparator_tFileInputDelimited_2 = ((String) ",").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_2[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\n").length() > 0) {
					rowSeparator_tFileInputDelimited_2 = ((String) "\n").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_2 = /** Start field tFileInputDelimited_2:FILENAME */
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/medals.csv"/**
																																 * End
																																 * field
																																 * tFileInputDelimited_2:FILENAME
																																 */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_2 = null;

				try {

					String[] rowtFileInputDelimited_2 = null;
					int currentLinetFileInputDelimited_2 = 0;
					int outputLinetFileInputDelimited_2 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_2 = 0;
							if (footer_value_tFileInputDelimited_2 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_2,
									fieldSeparator_tFileInputDelimited_2[0], "UTF-8");
						} else {
							csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_2),
									fieldSeparator_tFileInputDelimited_2[0], "UTF-8");
						}

						csvReadertFileInputDelimited_2.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_2[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_2[0] != '\r'))
							csvReadertFileInputDelimited_2.setLineEnd("" + rowSeparator_tFileInputDelimited_2[0]);

						csvReadertFileInputDelimited_2.setQuoteChar('"');

						csvReadertFileInputDelimited_2.setEscapeChar(csvReadertFileInputDelimited_2.getQuoteChar());

						if (footer_tFileInputDelimited_2 > 0) {
							for (totalLinetFileInputDelimited_2 = 0; totalLinetFileInputDelimited_2 < 1; totalLinetFileInputDelimited_2++) {
								csvReadertFileInputDelimited_2.readNext();
							}
							csvReadertFileInputDelimited_2.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_2.readNext()) {

								totalLinetFileInputDelimited_2++;

							}
							int lastLineTemptFileInputDelimited_2 = totalLinetFileInputDelimited_2
									- footer_tFileInputDelimited_2 < 0 ? 0
											: totalLinetFileInputDelimited_2 - footer_tFileInputDelimited_2;
							if (lastLinetFileInputDelimited_2 > 0) {
								lastLinetFileInputDelimited_2 = lastLinetFileInputDelimited_2 < lastLineTemptFileInputDelimited_2
										? lastLinetFileInputDelimited_2
										: lastLineTemptFileInputDelimited_2;
							} else {
								lastLinetFileInputDelimited_2 = lastLineTemptFileInputDelimited_2;
							}

							csvReadertFileInputDelimited_2.close();
							if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_2,
										fieldSeparator_tFileInputDelimited_2[0], "UTF-8");
							} else {
								csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_2),
										fieldSeparator_tFileInputDelimited_2[0], "UTF-8");
							}
							csvReadertFileInputDelimited_2.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_2[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_2[0] != '\r'))
								csvReadertFileInputDelimited_2.setLineEnd("" + rowSeparator_tFileInputDelimited_2[0]);

							csvReadertFileInputDelimited_2.setQuoteChar('"');

							csvReadertFileInputDelimited_2.setEscapeChar(csvReadertFileInputDelimited_2.getQuoteChar());

						}

						if (limittFileInputDelimited_2 != 0) {
							for (currentLinetFileInputDelimited_2 = 0; currentLinetFileInputDelimited_2 < 1; currentLinetFileInputDelimited_2++) {
								csvReadertFileInputDelimited_2.readNext();
							}
						}
						csvReadertFileInputDelimited_2.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					while (limittFileInputDelimited_2 != 0 && csvReadertFileInputDelimited_2 != null
							&& csvReadertFileInputDelimited_2.readNext()) {
						rowstate_tFileInputDelimited_2.reset();

						rowtFileInputDelimited_2 = csvReadertFileInputDelimited_2.getValues();

						currentLinetFileInputDelimited_2++;

						if (lastLinetFileInputDelimited_2 > -1
								&& currentLinetFileInputDelimited_2 > lastLinetFileInputDelimited_2) {
							break;
						}
						outputLinetFileInputDelimited_2++;
						if (limittFileInputDelimited_2 > 0
								&& outputLinetFileInputDelimited_2 > limittFileInputDelimited_2) {
							break;
						}

						row4 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row4 = new row4Struct();
						try {

							char fieldSeparator_tFileInputDelimited_2_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) ",").length() > 0) {
								fieldSeparator_tFileInputDelimited_2_ListType = ((String) ",").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_2.length == 1 && ("\015").equals(rowtFileInputDelimited_2[0])) {// empty
																														// line
																														// when
																														// row
																														// separator
																														// is
																														// '\n'

								row4.medal_type = null;

								row4.medal_code = null;

								row4.medal_date = null;

								row4.name = null;

								row4.gender = null;

								row4.discipline = null;

								row4.event = null;

								row4.event_type = null;

								row4.url_event = null;

								row4.code = null;

								row4.country_code = null;

								row4.country = null;

								row4.country_long = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_2 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_2 = 0;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.medal_type = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.medal_type = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 1;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row4.medal_code = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2]);

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"medal_code", "row4",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row4.medal_code = null;

									}

								} else {

									row4.medal_code = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 2;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row4.medal_date = ParserUtils.parseTo_Date(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
													"yyyy-mm-dd");

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"medal_date", "row4",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row4.medal_date = null;

									}

								} else {

									row4.medal_date = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 3;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.name = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.name = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 4;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row4.gender = ParserUtils.parseTo_Character(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2]);

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"gender", "row4",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row4.gender = null;

									}

								} else {

									row4.gender = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 5;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.discipline = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.discipline = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 6;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.event = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.event = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 7;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.event_type = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.event_type = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 8;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.url_event = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.url_event = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 9;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.code = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.code = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 10;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.country_code = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.country_code = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 11;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.country = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.country = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 12;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.country_long = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.country_long = null;

								}

							}

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							System.err.println(e.getMessage());
							row4 = null;

							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						}

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */
// Start of branch "row4"
						if (row4 != null) {

							/**
							 * [tUniqRow_2 main ] start
							 */

							currentComponent = "tUniqRow_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row4"

								);
							}

							row6 = null;
							row5 = null;
							if (row4.medal_type == null) {
								finder_tUniqRow_2.medal_type = null;
							} else {
								finder_tUniqRow_2.medal_type = row4.medal_type.toLowerCase();
							}
							finder_tUniqRow_2.medal_code = row4.medal_code;
							finder_tUniqRow_2.medal_date = row4.medal_date;
							if (row4.name == null) {
								finder_tUniqRow_2.name = null;
							} else {
								finder_tUniqRow_2.name = row4.name.toLowerCase();
							}
							finder_tUniqRow_2.gender = row4.gender;
							if (row4.discipline == null) {
								finder_tUniqRow_2.discipline = null;
							} else {
								finder_tUniqRow_2.discipline = row4.discipline.toLowerCase();
							}
							if (row4.event == null) {
								finder_tUniqRow_2.event = null;
							} else {
								finder_tUniqRow_2.event = row4.event.toLowerCase();
							}
							if (row4.event_type == null) {
								finder_tUniqRow_2.event_type = null;
							} else {
								finder_tUniqRow_2.event_type = row4.event_type.toLowerCase();
							}
							if (row4.url_event == null) {
								finder_tUniqRow_2.url_event = null;
							} else {
								finder_tUniqRow_2.url_event = row4.url_event.toLowerCase();
							}
							if (row4.code == null) {
								finder_tUniqRow_2.code = null;
							} else {
								finder_tUniqRow_2.code = row4.code.toLowerCase();
							}
							if (row4.country_code == null) {
								finder_tUniqRow_2.country_code = null;
							} else {
								finder_tUniqRow_2.country_code = row4.country_code.toLowerCase();
							}
							if (row4.country == null) {
								finder_tUniqRow_2.country = null;
							} else {
								finder_tUniqRow_2.country = row4.country.toLowerCase();
							}
							if (row4.country_long == null) {
								finder_tUniqRow_2.country_long = null;
							} else {
								finder_tUniqRow_2.country_long = row4.country_long.toLowerCase();
							}
							finder_tUniqRow_2.hashCodeDirty = true;
							if (!keystUniqRow_2.contains(finder_tUniqRow_2)) {
								KeyStruct_tUniqRow_2 new_tUniqRow_2 = new KeyStruct_tUniqRow_2();

								if (row4.medal_type == null) {
									new_tUniqRow_2.medal_type = null;
								} else {
									new_tUniqRow_2.medal_type = row4.medal_type.toLowerCase();
								}
								new_tUniqRow_2.medal_code = row4.medal_code;
								new_tUniqRow_2.medal_date = row4.medal_date;
								if (row4.name == null) {
									new_tUniqRow_2.name = null;
								} else {
									new_tUniqRow_2.name = row4.name.toLowerCase();
								}
								new_tUniqRow_2.gender = row4.gender;
								if (row4.discipline == null) {
									new_tUniqRow_2.discipline = null;
								} else {
									new_tUniqRow_2.discipline = row4.discipline.toLowerCase();
								}
								if (row4.event == null) {
									new_tUniqRow_2.event = null;
								} else {
									new_tUniqRow_2.event = row4.event.toLowerCase();
								}
								if (row4.event_type == null) {
									new_tUniqRow_2.event_type = null;
								} else {
									new_tUniqRow_2.event_type = row4.event_type.toLowerCase();
								}
								if (row4.url_event == null) {
									new_tUniqRow_2.url_event = null;
								} else {
									new_tUniqRow_2.url_event = row4.url_event.toLowerCase();
								}
								if (row4.code == null) {
									new_tUniqRow_2.code = null;
								} else {
									new_tUniqRow_2.code = row4.code.toLowerCase();
								}
								if (row4.country_code == null) {
									new_tUniqRow_2.country_code = null;
								} else {
									new_tUniqRow_2.country_code = row4.country_code.toLowerCase();
								}
								if (row4.country == null) {
									new_tUniqRow_2.country = null;
								} else {
									new_tUniqRow_2.country = row4.country.toLowerCase();
								}
								if (row4.country_long == null) {
									new_tUniqRow_2.country_long = null;
								} else {
									new_tUniqRow_2.country_long = row4.country_long.toLowerCase();
								}

								keystUniqRow_2.add(new_tUniqRow_2);
								if (row5 == null) {

									row5 = new row5Struct();
								}
								row5.medal_type = row4.medal_type;
								row5.medal_code = row4.medal_code;
								row5.medal_date = row4.medal_date;
								row5.name = row4.name;
								row5.gender = row4.gender;
								row5.discipline = row4.discipline;
								row5.event = row4.event;
								row5.event_type = row4.event_type;
								row5.url_event = row4.url_event;
								row5.code = row4.code;
								row5.country_code = row4.country_code;
								row5.country = row4.country;
								row5.country_long = row4.country_long;
								nb_uniques_tUniqRow_2++;
							} else {
								if (row6 == null) {

									row6 = new row6Struct();
								}
								row6.medal_type = row4.medal_type;
								row6.medal_code = row4.medal_code;
								row6.medal_date = row4.medal_date;
								row6.name = row4.name;
								row6.gender = row4.gender;
								row6.discipline = row4.discipline;
								row6.event = row4.event;
								row6.event_type = row4.event_type;
								row6.url_event = row4.url_event;
								row6.code = row4.code;
								row6.country_code = row4.country_code;
								row6.country = row4.country;
								row6.country_long = row4.country_long;
								nb_duplicates_tUniqRow_2++;
							}

							tos_count_tUniqRow_2++;

							/**
							 * [tUniqRow_2 main ] stop
							 */

							/**
							 * [tUniqRow_2 process_data_begin ] start
							 */

							currentComponent = "tUniqRow_2";

							/**
							 * [tUniqRow_2 process_data_begin ] stop
							 */
// Start of branch "row5"
							if (row5 != null) {

								/**
								 * [tLogRow_3 main ] start
								 */

								currentComponent = "tLogRow_3";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row5"

									);
								}

///////////////////////		

								strBuffer_tLogRow_3 = new StringBuilder();

								if (row5.medal_type != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.medal_type));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.medal_code != null) { //

									strBuffer_tLogRow_3.append(FormatterUtils.formatUnwithE(row5.medal_code));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.medal_date != null) { //

									strBuffer_tLogRow_3
											.append(FormatterUtils.format_Date(row5.medal_date, "yyyy-mm-dd"));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.name != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.name));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.gender != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.gender));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.discipline != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.discipline));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.event != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.event));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.event_type != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.event_type));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.url_event != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.url_event));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.code != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.code));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.country_code != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.country_code));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.country != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.country));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.country_long != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.country_long));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_3 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_3 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_3);
								}
								consoleOut_tLogRow_3.println(strBuffer_tLogRow_3.toString());
								consoleOut_tLogRow_3.flush();
								nb_line_tLogRow_3++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_3++;

								/**
								 * [tLogRow_3 main ] stop
								 */

								/**
								 * [tLogRow_3 process_data_begin ] start
								 */

								currentComponent = "tLogRow_3";

								/**
								 * [tLogRow_3 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_3 process_data_end ] start
								 */

								currentComponent = "tLogRow_3";

								/**
								 * [tLogRow_3 process_data_end ] stop
								 */

							} // End of branch "row5"

// Start of branch "row6"
							if (row6 != null) {

								/**
								 * [tLogRow_4 main ] start
								 */

								currentComponent = "tLogRow_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row6"

									);
								}

///////////////////////		

								strBuffer_tLogRow_4 = new StringBuilder();

								if (row6.medal_type != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.medal_type));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.medal_code != null) { //

									strBuffer_tLogRow_4.append(FormatterUtils.formatUnwithE(row6.medal_code));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.medal_date != null) { //

									strBuffer_tLogRow_4
											.append(FormatterUtils.format_Date(row6.medal_date, "yyyy-mm-dd"));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.name != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.name));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.gender != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.gender));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.discipline != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.discipline));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.event != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.event));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.event_type != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.event_type));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.url_event != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.url_event));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.code != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.code));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.country_code != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.country_code));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.country != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.country));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.country_long != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.country_long));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_4 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_4 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_4);
								}
								consoleOut_tLogRow_4.println(strBuffer_tLogRow_4.toString());
								consoleOut_tLogRow_4.flush();
								nb_line_tLogRow_4++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_4++;

								/**
								 * [tLogRow_4 main ] stop
								 */

								/**
								 * [tLogRow_4 process_data_begin ] start
								 */

								currentComponent = "tLogRow_4";

								/**
								 * [tLogRow_4 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_4 process_data_end ] start
								 */

								currentComponent = "tLogRow_4";

								/**
								 * [tLogRow_4 process_data_end ] stop
								 */

							} // End of branch "row6"

							/**
							 * [tUniqRow_2 process_data_end ] start
							 */

							currentComponent = "tUniqRow_2";

							/**
							 * [tUniqRow_2 process_data_end ] stop
							 */

						} // End of branch "row4"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						nb_line_tFileInputDelimited_2++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_2 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_2 != null) {
							csvReadertFileInputDelimited_2.close();
						}
					}
					if (csvReadertFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", nb_line_tFileInputDelimited_2);
					}

				}

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tUniqRow_2 end ] start
				 */

				currentComponent = "tUniqRow_2";

				globalMap.put("tUniqRow_2_NB_UNIQUES", nb_uniques_tUniqRow_2);
				globalMap.put("tUniqRow_2_NB_DUPLICATES", nb_duplicates_tUniqRow_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tUniqRow_2", true);
				end_Hash.put("tUniqRow_2", System.currentTimeMillis());

				/**
				 * [tUniqRow_2 end ] stop
				 */

				/**
				 * [tLogRow_3 end ] start
				 */

				currentComponent = "tLogRow_3";

//////
//////
				globalMap.put("tLogRow_3_NB_LINE", nb_line_tLogRow_3);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tLogRow_3", true);
				end_Hash.put("tLogRow_3", System.currentTimeMillis());

				/**
				 * [tLogRow_3 end ] stop
				 */

				/**
				 * [tLogRow_4 end ] start
				 */

				currentComponent = "tLogRow_4";

//////
//////
				globalMap.put("tLogRow_4_NB_LINE", nb_line_tLogRow_4);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tLogRow_4", true);
				end_Hash.put("tLogRow_4", System.currentTimeMillis());

				/**
				 * [tLogRow_4 end ] stop
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
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tUniqRow_2 finally ] start
				 */

				currentComponent = "tUniqRow_2";

				/**
				 * [tUniqRow_2 finally ] stop
				 */

				/**
				 * [tLogRow_3 finally ] start
				 */

				currentComponent = "tLogRow_3";

				/**
				 * [tLogRow_3 finally ] stop
				 */

				/**
				 * [tLogRow_4 finally ] start
				 */

				currentComponent = "tLogRow_4";

				/**
				 * [tLogRow_4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
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
		final doublons_medallists doublons_medallistsClass = new doublons_medallists();

		int exitCode = doublons_medallistsClass.runJobInTOS(args);

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
			java.io.InputStream inContext = doublons_medallists.class.getClassLoader().getResourceAsStream(
					"projettalend1/doublons_medallists_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = doublons_medallists.class.getClassLoader()
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
		try {
			errorCode = null;
			tFileInputDelimited_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_2) {
			globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_2.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : doublons_medallists");
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
 * 210155 characters generated by Talend Open Studio for Data Integration on the
 * 3 janvier 2025 à 09:47:56 CET
 ************************************************************************************************/