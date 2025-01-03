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

package projettalend1.completude_teams_0_1;

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
 * Job: completude_teams Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class completude_teams implements TalendJob {

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
	private final String jobName = "completude_teams";
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
					completude_teams.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(completude_teams.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFilterRow_1_error(Exception exception, String errorComponent,
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

	public void tFilterRow_2_error(Exception exception, String errorComponent,
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

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
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

	public void tFileInputDelimited_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_teams = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_teams = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public Character team_gender;

		public Character getTeam_gender() {
			return this.team_gender;
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

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String disciplines_code;

		public String getDisciplines_code() {
			return this.disciplines_code;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public String athletes;

		public String getAthletes() {
			return this.athletes;
		}

		public String coaches;

		public String getCoaches() {
			return this.coaches;
		}

		public String athletes_codes;

		public String getAthletes_codes() {
			return this.athletes_codes;
		}

		public String num_athletes;

		public String getNum_athletes() {
			return this.num_athletes;
		}

		public String coaches_codes;

		public String getCoaches_codes() {
			return this.coaches_codes;
		}

		public String num_coaches;

		public String getNum_coaches() {
			return this.num_coaches;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",current=" + current);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + String.valueOf(team_gender));
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",discipline=" + discipline);
			sb.append(",disciplines_code=" + disciplines_code);
			sb.append(",events=" + events);
			sb.append(",athletes=" + athletes);
			sb.append(",coaches=" + coaches);
			sb.append(",athletes_codes=" + athletes_codes);
			sb.append(",num_athletes=" + num_athletes);
			sb.append(",coaches_codes=" + coaches_codes);
			sb.append(",num_coaches=" + num_coaches);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_teams = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_teams = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public Character team_gender;

		public Character getTeam_gender() {
			return this.team_gender;
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

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String disciplines_code;

		public String getDisciplines_code() {
			return this.disciplines_code;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public String athletes;

		public String getAthletes() {
			return this.athletes;
		}

		public String coaches;

		public String getCoaches() {
			return this.coaches;
		}

		public String athletes_codes;

		public String getAthletes_codes() {
			return this.athletes_codes;
		}

		public String num_athletes;

		public String getNum_athletes() {
			return this.num_athletes;
		}

		public String coaches_codes;

		public String getCoaches_codes() {
			return this.coaches_codes;
		}

		public String num_coaches;

		public String getNum_coaches() {
			return this.num_coaches;
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

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
			sb.append(",current=" + current);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + String.valueOf(team_gender));
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",discipline=" + discipline);
			sb.append(",disciplines_code=" + disciplines_code);
			sb.append(",events=" + events);
			sb.append(",athletes=" + athletes);
			sb.append(",coaches=" + coaches);
			sb.append(",athletes_codes=" + athletes_codes);
			sb.append(",num_athletes=" + num_athletes);
			sb.append(",coaches_codes=" + coaches_codes);
			sb.append(",num_coaches=" + num_coaches);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_teams = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_teams = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public Character team_gender;

		public Character getTeam_gender() {
			return this.team_gender;
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

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String disciplines_code;

		public String getDisciplines_code() {
			return this.disciplines_code;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public String athletes;

		public String getAthletes() {
			return this.athletes;
		}

		public String coaches;

		public String getCoaches() {
			return this.coaches;
		}

		public String athletes_codes;

		public String getAthletes_codes() {
			return this.athletes_codes;
		}

		public String num_athletes;

		public String getNum_athletes() {
			return this.num_athletes;
		}

		public String coaches_codes;

		public String getCoaches_codes() {
			return this.coaches_codes;
		}

		public String num_coaches;

		public String getNum_coaches() {
			return this.num_coaches;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",current=" + current);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + String.valueOf(team_gender));
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",discipline=" + discipline);
			sb.append(",disciplines_code=" + disciplines_code);
			sb.append(",events=" + events);
			sb.append(",athletes=" + athletes);
			sb.append(",coaches=" + coaches);
			sb.append(",athletes_codes=" + athletes_codes);
			sb.append(",num_athletes=" + num_athletes);
			sb.append(",coaches_codes=" + coaches_codes);
			sb.append(",num_coaches=" + num_coaches);
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
				 * [tFilterRow_1 begin ] start
				 */

				ok_Hash.put("tFilterRow_1", false);
				start_Hash.put("tFilterRow_1", System.currentTimeMillis());

				currentComponent = "tFilterRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tFilterRow_1 = 0;

				int nb_line_tFilterRow_1 = 0;
				int nb_line_ok_tFilterRow_1 = 0;
				int nb_line_reject_tFilterRow_1 = 0;

				class Operator_tFilterRow_1 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_1(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_1 begin ] stop
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
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/teams.csv"/**
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

								row1.current = null;

								row1.team = null;

								row1.team_gender = null;

								row1.country_code = null;

								row1.country = null;

								row1.country_long = null;

								row1.discipline = null;

								row1.disciplines_code = null;

								row1.events = null;

								row1.athletes = null;

								row1.coaches = null;

								row1.athletes_codes = null;

								row1.num_athletes = null;

								row1.coaches_codes = null;

								row1.num_coaches = null;

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

									row1.current = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.current = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 2;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.team = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.team = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 3;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row1.team_gender = ParserUtils.parseTo_Character(
													rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]);

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"team_gender", "row1",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row1.team_gender = null;

									}

								} else {

									row1.team_gender = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 4;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country_code = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country_code = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 5;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 6;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country_long = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 7;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.discipline = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.discipline = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 8;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.disciplines_code = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.disciplines_code = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 9;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.events = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.events = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 10;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.athletes = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.athletes = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 11;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.coaches = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.coaches = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 12;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.athletes_codes = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.athletes_codes = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 13;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.num_athletes = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.num_athletes = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 14;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.coaches_codes = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.coaches_codes = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 15;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.num_coaches = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.num_coaches = null;

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
							 * [tFilterRow_1 main ] start
							 */

							currentComponent = "tFilterRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							row3 = null;
							row2 = null;
							Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("||");
							ope_tFilterRow_1.matches((row1.coaches == null ? false : row1.coaches.compareTo("") != 0),
									"coaches.compareTo(\"\") != 0 failed");
							ope_tFilterRow_1.matches(
									(row1.coaches_codes == null ? false : row1.coaches_codes.compareTo("") != 0),
									"coaches_codes.compareTo(\"\") != 0 failed");
							ope_tFilterRow_1.matches(
									(row1.num_coaches == null ? false : row1.num_coaches.compareTo("") != 0),
									"num_coaches.compareTo(\"\") != 0 failed");

							if (ope_tFilterRow_1.getMatchFlag()) {
								if (row2 == null) {
									row2 = new row2Struct();
								}
								row2.code = row1.code;
								row2.current = row1.current;
								row2.team = row1.team;
								row2.team_gender = row1.team_gender;
								row2.country_code = row1.country_code;
								row2.country = row1.country;
								row2.country_long = row1.country_long;
								row2.discipline = row1.discipline;
								row2.disciplines_code = row1.disciplines_code;
								row2.events = row1.events;
								row2.athletes = row1.athletes;
								row2.coaches = row1.coaches;
								row2.athletes_codes = row1.athletes_codes;
								row2.num_athletes = row1.num_athletes;
								row2.coaches_codes = row1.coaches_codes;
								row2.num_coaches = row1.num_coaches;
								nb_line_ok_tFilterRow_1++;
							} else {
								if (row3 == null) {
									row3 = new row3Struct();
								}
								row3.code = row1.code;
								row3.current = row1.current;
								row3.team = row1.team;
								row3.team_gender = row1.team_gender;
								row3.country_code = row1.country_code;
								row3.country = row1.country;
								row3.country_long = row1.country_long;
								row3.discipline = row1.discipline;
								row3.disciplines_code = row1.disciplines_code;
								row3.events = row1.events;
								row3.athletes = row1.athletes;
								row3.coaches = row1.coaches;
								row3.athletes_codes = row1.athletes_codes;
								row3.num_athletes = row1.num_athletes;
								row3.coaches_codes = row1.coaches_codes;
								row3.num_coaches = row1.num_coaches;
								row3.errorMessage = ope_tFilterRow_1.getErrorMsg();
								nb_line_reject_tFilterRow_1++;
							}

							nb_line_tFilterRow_1++;

							tos_count_tFilterRow_1++;

							/**
							 * [tFilterRow_1 main ] stop
							 */

							/**
							 * [tFilterRow_1 process_data_begin ] start
							 */

							currentComponent = "tFilterRow_1";

							/**
							 * [tFilterRow_1 process_data_begin ] stop
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

								if (row2.current != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.current));

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

								if (row2.discipline != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.discipline));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.disciplines_code != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.disciplines_code));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.events != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.events));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.athletes != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.athletes));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.coaches != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.coaches));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.athletes_codes != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.athletes_codes));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.num_athletes != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.num_athletes));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.coaches_codes != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.coaches_codes));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row2.num_coaches != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row2.num_coaches));

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

								if (row3.current != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.current));

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

								if (row3.discipline != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.discipline));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.disciplines_code != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.disciplines_code));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.events != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.events));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.athletes != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.athletes));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.coaches != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.coaches));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.athletes_codes != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.athletes_codes));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.num_athletes != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.num_athletes));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.coaches_codes != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.coaches_codes));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row3.num_coaches != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row3.num_coaches));

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
							 * [tFilterRow_1 process_data_end ] start
							 */

							currentComponent = "tFilterRow_1";

							/**
							 * [tFilterRow_1 process_data_end ] stop
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
				 * [tFilterRow_1 end ] start
				 */

				currentComponent = "tFilterRow_1";

				globalMap.put("tFilterRow_1_NB_LINE", nb_line_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_OK", nb_line_ok_tFilterRow_1);
				globalMap.put("tFilterRow_1_NB_LINE_REJECT", nb_line_reject_tFilterRow_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tFilterRow_1", true);
				end_Hash.put("tFilterRow_1", System.currentTimeMillis());

				/**
				 * [tFilterRow_1 end ] stop
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
				 * [tFilterRow_1 finally ] start
				 */

				currentComponent = "tFilterRow_1";

				/**
				 * [tFilterRow_1 finally ] stop
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_teams = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_teams = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public Character team_gender;

		public Character getTeam_gender() {
			return this.team_gender;
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

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String disciplines_code;

		public String getDisciplines_code() {
			return this.disciplines_code;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public String athletes;

		public String getAthletes() {
			return this.athletes;
		}

		public String coaches;

		public String getCoaches() {
			return this.coaches;
		}

		public String athletes_codes;

		public String getAthletes_codes() {
			return this.athletes_codes;
		}

		public String num_athletes;

		public String getNum_athletes() {
			return this.num_athletes;
		}

		public String coaches_codes;

		public String getCoaches_codes() {
			return this.coaches_codes;
		}

		public String num_coaches;

		public String getNum_coaches() {
			return this.num_coaches;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",current=" + current);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + String.valueOf(team_gender));
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",discipline=" + discipline);
			sb.append(",disciplines_code=" + disciplines_code);
			sb.append(",events=" + events);
			sb.append(",athletes=" + athletes);
			sb.append(",coaches=" + coaches);
			sb.append(",athletes_codes=" + athletes_codes);
			sb.append(",num_athletes=" + num_athletes);
			sb.append(",coaches_codes=" + coaches_codes);
			sb.append(",num_coaches=" + num_coaches);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_teams = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_teams = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public Character team_gender;

		public Character getTeam_gender() {
			return this.team_gender;
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

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String disciplines_code;

		public String getDisciplines_code() {
			return this.disciplines_code;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public String athletes;

		public String getAthletes() {
			return this.athletes;
		}

		public String coaches;

		public String getCoaches() {
			return this.coaches;
		}

		public String athletes_codes;

		public String getAthletes_codes() {
			return this.athletes_codes;
		}

		public String num_athletes;

		public String getNum_athletes() {
			return this.num_athletes;
		}

		public String coaches_codes;

		public String getCoaches_codes() {
			return this.coaches_codes;
		}

		public String num_coaches;

		public String getNum_coaches() {
			return this.num_coaches;
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

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
			sb.append(",current=" + current);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + String.valueOf(team_gender));
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",discipline=" + discipline);
			sb.append(",disciplines_code=" + disciplines_code);
			sb.append(",events=" + events);
			sb.append(",athletes=" + athletes);
			sb.append(",coaches=" + coaches);
			sb.append(",athletes_codes=" + athletes_codes);
			sb.append(",num_athletes=" + num_athletes);
			sb.append(",coaches_codes=" + coaches_codes);
			sb.append(",num_coaches=" + num_coaches);
			sb.append(",errorMessage=" + errorMessage);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_teams = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_teams = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public Character team_gender;

		public Character getTeam_gender() {
			return this.team_gender;
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

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String disciplines_code;

		public String getDisciplines_code() {
			return this.disciplines_code;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public String athletes;

		public String getAthletes() {
			return this.athletes;
		}

		public String coaches;

		public String getCoaches() {
			return this.coaches;
		}

		public String athletes_codes;

		public String getAthletes_codes() {
			return this.athletes_codes;
		}

		public String num_athletes;

		public String getNum_athletes() {
			return this.num_athletes;
		}

		public String coaches_codes;

		public String getCoaches_codes() {
			return this.coaches_codes;
		}

		public String num_coaches;

		public String getNum_coaches() {
			return this.num_coaches;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",current=" + current);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + String.valueOf(team_gender));
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",discipline=" + discipline);
			sb.append(",disciplines_code=" + disciplines_code);
			sb.append(",events=" + events);
			sb.append(",athletes=" + athletes);
			sb.append(",coaches=" + coaches);
			sb.append(",athletes_codes=" + athletes_codes);
			sb.append(",num_athletes=" + num_athletes);
			sb.append(",coaches_codes=" + coaches_codes);
			sb.append(",num_coaches=" + num_coaches);
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
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/ProjetEntrepot/out.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				if (filetFileOutputDelimited_1.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_1.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_1.length() == 0) {
					outtFileOutputDelimited_1.write("code");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("current");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("team");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("team_gender");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("country_code");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("country");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("country_long");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("discipline");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("disciplines_code");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("events");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("athletes");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("coaches");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("athletes_codes");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("num_athletes");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("coaches_codes");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("num_coaches");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("errorMessage");
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tFilterRow_2 begin ] start
				 */

				ok_Hash.put("tFilterRow_2", false);
				start_Hash.put("tFilterRow_2", System.currentTimeMillis());

				currentComponent = "tFilterRow_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tFilterRow_2 = 0;

				int nb_line_tFilterRow_2 = 0;
				int nb_line_ok_tFilterRow_2 = 0;
				int nb_line_reject_tFilterRow_2 = 0;

				class Operator_tFilterRow_2 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_2(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_2 begin ] stop
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
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/teams.csv"/**
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

								row4.code = null;

								row4.current = null;

								row4.team = null;

								row4.team_gender = null;

								row4.country_code = null;

								row4.country = null;

								row4.country_long = null;

								row4.discipline = null;

								row4.disciplines_code = null;

								row4.events = null;

								row4.athletes = null;

								row4.coaches = null;

								row4.athletes_codes = null;

								row4.num_athletes = null;

								row4.coaches_codes = null;

								row4.num_coaches = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_2 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_2 = 0;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.code = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.code = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 1;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.current = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.current = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 2;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.team = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.team = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 3;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row4.team_gender = ParserUtils.parseTo_Character(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2]);

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"team_gender", "row4",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row4.team_gender = null;

									}

								} else {

									row4.team_gender = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 4;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.country_code = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.country_code = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 5;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.country = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.country = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 6;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.country_long = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 7;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.discipline = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.discipline = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 8;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.disciplines_code = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.disciplines_code = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 9;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.events = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.events = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 10;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.athletes = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.athletes = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 11;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.coaches = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.coaches = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 12;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.athletes_codes = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.athletes_codes = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 13;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.num_athletes = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.num_athletes = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 14;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.coaches_codes = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.coaches_codes = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 15;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.num_coaches = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.num_coaches = null;

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
							row6 = null;

							/**
							 * [tFilterRow_2 main ] start
							 */

							currentComponent = "tFilterRow_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row4"

								);
							}

							row6 = null;
							row5 = null;
							Operator_tFilterRow_2 ope_tFilterRow_2 = new Operator_tFilterRow_2("&&");
							ope_tFilterRow_2.matches((row4.code == null ? false : row4.code.compareTo("") != 0),
									"code.compareTo(\"\") != 0 failed");
							ope_tFilterRow_2.matches(
									(row4.country_code == null ? false : row4.country_code.compareTo("") != 0),
									"country_code.compareTo(\"\") != 0 failed");
							ope_tFilterRow_2.matches(
									(row4.disciplines_code == null ? false : row4.disciplines_code.compareTo("") != 0),
									"disciplines_code.compareTo(\"\") != 0 failed");
							ope_tFilterRow_2.matches((row4.athletes == null ? false : row4.athletes.compareTo("") != 0),
									"athletes.compareTo(\"\") != 0 failed");
							ope_tFilterRow_2.matches((row4.events == null ? false : row4.events.compareTo("") != 0),
									"events.compareTo(\"\") != 0 failed");

							if (ope_tFilterRow_2.getMatchFlag()) {
								if (row5 == null) {
									row5 = new row5Struct();
								}
								row5.code = row4.code;
								row5.current = row4.current;
								row5.team = row4.team;
								row5.team_gender = row4.team_gender;
								row5.country_code = row4.country_code;
								row5.country = row4.country;
								row5.country_long = row4.country_long;
								row5.discipline = row4.discipline;
								row5.disciplines_code = row4.disciplines_code;
								row5.events = row4.events;
								row5.athletes = row4.athletes;
								row5.coaches = row4.coaches;
								row5.athletes_codes = row4.athletes_codes;
								row5.num_athletes = row4.num_athletes;
								row5.coaches_codes = row4.coaches_codes;
								row5.num_coaches = row4.num_coaches;
								nb_line_ok_tFilterRow_2++;
							} else {
								if (row6 == null) {
									row6 = new row6Struct();
								}
								row6.code = row4.code;
								row6.current = row4.current;
								row6.team = row4.team;
								row6.team_gender = row4.team_gender;
								row6.country_code = row4.country_code;
								row6.country = row4.country;
								row6.country_long = row4.country_long;
								row6.discipline = row4.discipline;
								row6.disciplines_code = row4.disciplines_code;
								row6.events = row4.events;
								row6.athletes = row4.athletes;
								row6.coaches = row4.coaches;
								row6.athletes_codes = row4.athletes_codes;
								row6.num_athletes = row4.num_athletes;
								row6.coaches_codes = row4.coaches_codes;
								row6.num_coaches = row4.num_coaches;
								row6.errorMessage = ope_tFilterRow_2.getErrorMsg();
								nb_line_reject_tFilterRow_2++;
							}

							nb_line_tFilterRow_2++;

							tos_count_tFilterRow_2++;

							/**
							 * [tFilterRow_2 main ] stop
							 */

							/**
							 * [tFilterRow_2 process_data_begin ] start
							 */

							currentComponent = "tFilterRow_2";

							/**
							 * [tFilterRow_2 process_data_begin ] stop
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

								if (row5.code != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.code));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.current != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.current));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.team != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.team));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.team_gender != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.team_gender));

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

								strBuffer_tLogRow_3.append("|");

								if (row5.discipline != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.discipline));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.disciplines_code != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.disciplines_code));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.events != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.events));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.athletes != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.athletes));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.coaches != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.coaches));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.athletes_codes != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.athletes_codes));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.num_athletes != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.num_athletes));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.coaches_codes != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.coaches_codes));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.num_coaches != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.num_coaches));

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
								 * [tFileOutputDelimited_1 main ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row6"

									);
								}

								StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
								if (row6.code != null) {
									sb_tFileOutputDelimited_1.append(row6.code);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.current != null) {
									sb_tFileOutputDelimited_1.append(row6.current);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.team != null) {
									sb_tFileOutputDelimited_1.append(row6.team);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.team_gender != null) {
									sb_tFileOutputDelimited_1.append(row6.team_gender);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.country_code != null) {
									sb_tFileOutputDelimited_1.append(row6.country_code);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.country != null) {
									sb_tFileOutputDelimited_1.append(row6.country);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.country_long != null) {
									sb_tFileOutputDelimited_1.append(row6.country_long);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.discipline != null) {
									sb_tFileOutputDelimited_1.append(row6.discipline);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.disciplines_code != null) {
									sb_tFileOutputDelimited_1.append(row6.disciplines_code);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.events != null) {
									sb_tFileOutputDelimited_1.append(row6.events);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.athletes != null) {
									sb_tFileOutputDelimited_1.append(row6.athletes);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.coaches != null) {
									sb_tFileOutputDelimited_1.append(row6.coaches);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.athletes_codes != null) {
									sb_tFileOutputDelimited_1.append(row6.athletes_codes);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.num_athletes != null) {
									sb_tFileOutputDelimited_1.append(row6.num_athletes);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.coaches_codes != null) {
									sb_tFileOutputDelimited_1.append(row6.coaches_codes);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.num_coaches != null) {
									sb_tFileOutputDelimited_1.append(row6.num_coaches);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row6.errorMessage != null) {
									sb_tFileOutputDelimited_1.append(row6.errorMessage);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

								nb_line_tFileOutputDelimited_1++;
								resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

								outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

								tos_count_tFileOutputDelimited_1++;

								/**
								 * [tFileOutputDelimited_1 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_end ] stop
								 */

							} // End of branch "row6"

							/**
							 * [tFilterRow_2 process_data_end ] start
							 */

							currentComponent = "tFilterRow_2";

							/**
							 * [tFilterRow_2 process_data_end ] stop
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
				 * [tFilterRow_2 end ] start
				 */

				currentComponent = "tFilterRow_2";

				globalMap.put("tFilterRow_2_NB_LINE", nb_line_tFilterRow_2);
				globalMap.put("tFilterRow_2_NB_LINE_OK", nb_line_ok_tFilterRow_2);
				globalMap.put("tFilterRow_2_NB_LINE_REJECT", nb_line_reject_tFilterRow_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tFilterRow_2", true);
				end_Hash.put("tFilterRow_2", System.currentTimeMillis());

				/**
				 * [tFilterRow_2 end ] stop
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
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
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
				 * [tFilterRow_2 finally ] start
				 */

				currentComponent = "tFilterRow_2";

				/**
				 * [tFilterRow_2 finally ] stop
				 */

				/**
				 * [tLogRow_3 finally ] start
				 */

				currentComponent = "tLogRow_3";

				/**
				 * [tLogRow_3 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
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

	public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_teams = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_teams = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public Character team_gender;

		public Character getTeam_gender() {
			return this.team_gender;
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

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String disciplines_code;

		public String getDisciplines_code() {
			return this.disciplines_code;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public String athletes;

		public String getAthletes() {
			return this.athletes;
		}

		public String coaches;

		public String getCoaches() {
			return this.coaches;
		}

		public String athletes_codes;

		public String getAthletes_codes() {
			return this.athletes_codes;
		}

		public String num_athletes;

		public String getNum_athletes() {
			return this.num_athletes;
		}

		public String coaches_codes;

		public String getCoaches_codes() {
			return this.coaches_codes;
		}

		public String num_coaches;

		public String getNum_coaches() {
			return this.num_coaches;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",current=" + current);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + String.valueOf(team_gender));
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",discipline=" + discipline);
			sb.append(",disciplines_code=" + disciplines_code);
			sb.append(",events=" + events);
			sb.append(",athletes=" + athletes);
			sb.append(",coaches=" + coaches);
			sb.append(",athletes_codes=" + athletes_codes);
			sb.append(",num_athletes=" + num_athletes);
			sb.append(",coaches_codes=" + coaches_codes);
			sb.append(",num_coaches=" + num_coaches);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

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

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_teams = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_teams = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public Character team_gender;

		public Character getTeam_gender() {
			return this.team_gender;
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

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String disciplines_code;

		public String getDisciplines_code() {
			return this.disciplines_code;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public String athletes;

		public String getAthletes() {
			return this.athletes;
		}

		public String coaches;

		public String getCoaches() {
			return this.coaches;
		}

		public String athletes_codes;

		public String getAthletes_codes() {
			return this.athletes_codes;
		}

		public String num_athletes;

		public String getNum_athletes() {
			return this.num_athletes;
		}

		public String coaches_codes;

		public String getCoaches_codes() {
			return this.coaches_codes;
		}

		public String num_coaches;

		public String getNum_coaches() {
			return this.num_coaches;
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

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
			sb.append(",current=" + current);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + String.valueOf(team_gender));
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",discipline=" + discipline);
			sb.append(",disciplines_code=" + disciplines_code);
			sb.append(",events=" + events);
			sb.append(",athletes=" + athletes);
			sb.append(",coaches=" + coaches);
			sb.append(",athletes_codes=" + athletes_codes);
			sb.append(",num_athletes=" + num_athletes);
			sb.append(",coaches_codes=" + coaches_codes);
			sb.append(",num_coaches=" + num_coaches);
			sb.append(",errorMessage=" + errorMessage);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

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

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_teams = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_teams = new byte[0];

		public String code;

		public String getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String team;

		public String getTeam() {
			return this.team;
		}

		public Character team_gender;

		public Character getTeam_gender() {
			return this.team_gender;
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

		public String discipline;

		public String getDiscipline() {
			return this.discipline;
		}

		public String disciplines_code;

		public String getDisciplines_code() {
			return this.disciplines_code;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public String athletes;

		public String getAthletes() {
			return this.athletes;
		}

		public String coaches;

		public String getCoaches() {
			return this.coaches;
		}

		public String athletes_codes;

		public String getAthletes_codes() {
			return this.athletes_codes;
		}

		public String num_athletes;

		public String getNum_athletes() {
			return this.num_athletes;
		}

		public String coaches_codes;

		public String getCoaches_codes() {
			return this.coaches_codes;
		}

		public String num_coaches;

		public String getNum_coaches() {
			return this.num_coaches;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_teams.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_teams.length == 0) {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_teams = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_teams, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_teams, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_teams) {

				try {

					int length = 0;

					this.code = readString(dis);

					this.current = readString(dis);

					this.team = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.team_gender = null;
					} else {
						this.team_gender = dis.readChar();
					}

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.discipline = readString(dis);

					this.disciplines_code = readString(dis);

					this.events = readString(dis);

					this.athletes = readString(dis);

					this.coaches = readString(dis);

					this.athletes_codes = readString(dis);

					this.num_athletes = readString(dis);

					this.coaches_codes = readString(dis);

					this.num_coaches = readString(dis);

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

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.team, dos);

				// Character

				if (this.team_gender == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.team_gender);
				}

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.discipline, dos);

				// String

				writeString(this.disciplines_code, dos);

				// String

				writeString(this.events, dos);

				// String

				writeString(this.athletes, dos);

				// String

				writeString(this.coaches, dos);

				// String

				writeString(this.athletes_codes, dos);

				// String

				writeString(this.num_athletes, dos);

				// String

				writeString(this.coaches_codes, dos);

				// String

				writeString(this.num_coaches, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + code);
			sb.append(",current=" + current);
			sb.append(",team=" + team);
			sb.append(",team_gender=" + String.valueOf(team_gender));
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",discipline=" + discipline);
			sb.append(",disciplines_code=" + disciplines_code);
			sb.append(",events=" + events);
			sb.append(",athletes=" + athletes);
			sb.append(",coaches=" + coaches);
			sb.append(",athletes_codes=" + athletes_codes);
			sb.append(",num_athletes=" + num_athletes);
			sb.append(",coaches_codes=" + coaches_codes);
			sb.append(",num_coaches=" + num_coaches);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

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

	public void tFileInputDelimited_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 0);

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

				row7Struct row7 = new row7Struct();
				row8Struct row8 = new row8Struct();
				row9Struct row9 = new row9Struct();

				/**
				 * [tLogRow_4 begin ] start
				 */

				ok_Hash.put("tLogRow_4", false);
				start_Hash.put("tLogRow_4", System.currentTimeMillis());

				currentComponent = "tLogRow_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row8");
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
				 * [tLogRow_5 begin ] start
				 */

				ok_Hash.put("tLogRow_5", false);
				start_Hash.put("tLogRow_5", System.currentTimeMillis());

				currentComponent = "tLogRow_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row9");
				}

				int tos_count_tLogRow_5 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_5 = "|";
				java.io.PrintStream consoleOut_tLogRow_5 = null;

				StringBuilder strBuffer_tLogRow_5 = null;
				int nb_line_tLogRow_5 = 0;
///////////////////////    			

				/**
				 * [tLogRow_5 begin ] stop
				 */

				/**
				 * [tFilterRow_3 begin ] start
				 */

				ok_Hash.put("tFilterRow_3", false);
				start_Hash.put("tFilterRow_3", System.currentTimeMillis());

				currentComponent = "tFilterRow_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
				}

				int tos_count_tFilterRow_3 = 0;

				int nb_line_tFilterRow_3 = 0;
				int nb_line_ok_tFilterRow_3 = 0;
				int nb_line_reject_tFilterRow_3 = 0;

				class Operator_tFilterRow_3 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_3(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_3 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_3", false);
				start_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_3";

				int tos_count_tFileInputDelimited_3 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_3 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_3 = 0;
				int footer_tFileInputDelimited_3 = 0;
				int totalLinetFileInputDelimited_3 = 0;
				int limittFileInputDelimited_3 = -1;
				int lastLinetFileInputDelimited_3 = -1;

				char fieldSeparator_tFileInputDelimited_3[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) ",").length() > 0) {
					fieldSeparator_tFileInputDelimited_3 = ((String) ",").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_3[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\n").length() > 0) {
					rowSeparator_tFileInputDelimited_3 = ((String) "\n").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_3 = /** Start field tFileInputDelimited_3:FILENAME */
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/teams.csv"/**
																																 * End
																																 * field
																																 * tFileInputDelimited_3:FILENAME
																																 */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_3 = null;

				try {

					String[] rowtFileInputDelimited_3 = null;
					int currentLinetFileInputDelimited_3 = 0;
					int outputLinetFileInputDelimited_3 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_3 = 0;
							if (footer_value_tFileInputDelimited_3 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_3 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_3,
									fieldSeparator_tFileInputDelimited_3[0], "UTF-8");
						} else {
							csvReadertFileInputDelimited_3 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_3),
									fieldSeparator_tFileInputDelimited_3[0], "UTF-8");
						}

						csvReadertFileInputDelimited_3.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_3[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_3[0] != '\r'))
							csvReadertFileInputDelimited_3.setLineEnd("" + rowSeparator_tFileInputDelimited_3[0]);

						csvReadertFileInputDelimited_3.setQuoteChar('"');

						csvReadertFileInputDelimited_3.setEscapeChar(csvReadertFileInputDelimited_3.getQuoteChar());

						if (footer_tFileInputDelimited_3 > 0) {
							for (totalLinetFileInputDelimited_3 = 0; totalLinetFileInputDelimited_3 < 1; totalLinetFileInputDelimited_3++) {
								csvReadertFileInputDelimited_3.readNext();
							}
							csvReadertFileInputDelimited_3.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_3.readNext()) {

								totalLinetFileInputDelimited_3++;

							}
							int lastLineTemptFileInputDelimited_3 = totalLinetFileInputDelimited_3
									- footer_tFileInputDelimited_3 < 0 ? 0
											: totalLinetFileInputDelimited_3 - footer_tFileInputDelimited_3;
							if (lastLinetFileInputDelimited_3 > 0) {
								lastLinetFileInputDelimited_3 = lastLinetFileInputDelimited_3 < lastLineTemptFileInputDelimited_3
										? lastLinetFileInputDelimited_3
										: lastLineTemptFileInputDelimited_3;
							} else {
								lastLinetFileInputDelimited_3 = lastLineTemptFileInputDelimited_3;
							}

							csvReadertFileInputDelimited_3.close();
							if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_3 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_3,
										fieldSeparator_tFileInputDelimited_3[0], "UTF-8");
							} else {
								csvReadertFileInputDelimited_3 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_3),
										fieldSeparator_tFileInputDelimited_3[0], "UTF-8");
							}
							csvReadertFileInputDelimited_3.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_3[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_3[0] != '\r'))
								csvReadertFileInputDelimited_3.setLineEnd("" + rowSeparator_tFileInputDelimited_3[0]);

							csvReadertFileInputDelimited_3.setQuoteChar('"');

							csvReadertFileInputDelimited_3.setEscapeChar(csvReadertFileInputDelimited_3.getQuoteChar());

						}

						if (limittFileInputDelimited_3 != 0) {
							for (currentLinetFileInputDelimited_3 = 0; currentLinetFileInputDelimited_3 < 1; currentLinetFileInputDelimited_3++) {
								csvReadertFileInputDelimited_3.readNext();
							}
						}
						csvReadertFileInputDelimited_3.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					while (limittFileInputDelimited_3 != 0 && csvReadertFileInputDelimited_3 != null
							&& csvReadertFileInputDelimited_3.readNext()) {
						rowstate_tFileInputDelimited_3.reset();

						rowtFileInputDelimited_3 = csvReadertFileInputDelimited_3.getValues();

						currentLinetFileInputDelimited_3++;

						if (lastLinetFileInputDelimited_3 > -1
								&& currentLinetFileInputDelimited_3 > lastLinetFileInputDelimited_3) {
							break;
						}
						outputLinetFileInputDelimited_3++;
						if (limittFileInputDelimited_3 > 0
								&& outputLinetFileInputDelimited_3 > limittFileInputDelimited_3) {
							break;
						}

						row7 = null;

						boolean whetherReject_tFileInputDelimited_3 = false;
						row7 = new row7Struct();
						try {

							char fieldSeparator_tFileInputDelimited_3_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) ",").length() > 0) {
								fieldSeparator_tFileInputDelimited_3_ListType = ((String) ",").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_3.length == 1 && ("\015").equals(rowtFileInputDelimited_3[0])) {// empty
																														// line
																														// when
																														// row
																														// separator
																														// is
																														// '\n'

								row7.code = null;

								row7.current = null;

								row7.team = null;

								row7.team_gender = null;

								row7.country_code = null;

								row7.country = null;

								row7.country_long = null;

								row7.discipline = null;

								row7.disciplines_code = null;

								row7.events = null;

								row7.athletes = null;

								row7.coaches = null;

								row7.athletes_codes = null;

								row7.num_athletes = null;

								row7.coaches_codes = null;

								row7.num_coaches = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_3 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_3 = 0;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.code = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.code = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 1;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.current = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.current = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 2;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.team = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.team = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 3;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									if (rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3].length() > 0) {
										try {

											row7.team_gender = ParserUtils.parseTo_Character(
													rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3]);

										} catch (java.lang.Exception ex_tFileInputDelimited_3) {
											globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
													ex_tFileInputDelimited_3.getMessage());
											rowstate_tFileInputDelimited_3.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"team_gender", "row7",
															rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
															ex_tFileInputDelimited_3),
													ex_tFileInputDelimited_3));
										}
									} else {

										row7.team_gender = null;

									}

								} else {

									row7.team_gender = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 4;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.country_code = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.country_code = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 5;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.country = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.country = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 6;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.country_long = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 7;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.discipline = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.discipline = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 8;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.disciplines_code = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.disciplines_code = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 9;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.events = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.events = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 10;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.athletes = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.athletes = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 11;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.coaches = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.coaches = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 12;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.athletes_codes = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.athletes_codes = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 13;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.num_athletes = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.num_athletes = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 14;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.coaches_codes = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.coaches_codes = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 15;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.num_coaches = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.num_coaches = null;

								}

							}

							if (rowstate_tFileInputDelimited_3.getException() != null) {
								throw rowstate_tFileInputDelimited_3.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_3 = true;

							System.err.println(e.getMessage());
							row7 = null;

							globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());

						}

						/**
						 * [tFileInputDelimited_3 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_3 main ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						tos_count_tFileInputDelimited_3++;

						/**
						 * [tFileInputDelimited_3 main ] stop
						 */

						/**
						 * [tFileInputDelimited_3 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_begin ] stop
						 */
// Start of branch "row7"
						if (row7 != null) {
							row9 = null;

							/**
							 * [tFilterRow_3 main ] start
							 */

							currentComponent = "tFilterRow_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row7"

								);
							}

							row9 = null;
							row8 = null;
							Operator_tFilterRow_3 ope_tFilterRow_3 = new Operator_tFilterRow_3("||");
							ope_tFilterRow_3.matches((row7.events == null ? false : row7.events.compareTo("") != 0),
									"events.compareTo(\"\") != 0 failed");

							if (ope_tFilterRow_3.getMatchFlag()) {
								if (row8 == null) {
									row8 = new row8Struct();
								}
								row8.code = row7.code;
								row8.current = row7.current;
								row8.team = row7.team;
								row8.team_gender = row7.team_gender;
								row8.country_code = row7.country_code;
								row8.country = row7.country;
								row8.country_long = row7.country_long;
								row8.discipline = row7.discipline;
								row8.disciplines_code = row7.disciplines_code;
								row8.events = row7.events;
								row8.athletes = row7.athletes;
								row8.coaches = row7.coaches;
								row8.athletes_codes = row7.athletes_codes;
								row8.num_athletes = row7.num_athletes;
								row8.coaches_codes = row7.coaches_codes;
								row8.num_coaches = row7.num_coaches;
								nb_line_ok_tFilterRow_3++;
							} else {
								if (row9 == null) {
									row9 = new row9Struct();
								}
								row9.code = row7.code;
								row9.current = row7.current;
								row9.team = row7.team;
								row9.team_gender = row7.team_gender;
								row9.country_code = row7.country_code;
								row9.country = row7.country;
								row9.country_long = row7.country_long;
								row9.discipline = row7.discipline;
								row9.disciplines_code = row7.disciplines_code;
								row9.events = row7.events;
								row9.athletes = row7.athletes;
								row9.coaches = row7.coaches;
								row9.athletes_codes = row7.athletes_codes;
								row9.num_athletes = row7.num_athletes;
								row9.coaches_codes = row7.coaches_codes;
								row9.num_coaches = row7.num_coaches;
								row9.errorMessage = ope_tFilterRow_3.getErrorMsg();
								nb_line_reject_tFilterRow_3++;
							}

							nb_line_tFilterRow_3++;

							tos_count_tFilterRow_3++;

							/**
							 * [tFilterRow_3 main ] stop
							 */

							/**
							 * [tFilterRow_3 process_data_begin ] start
							 */

							currentComponent = "tFilterRow_3";

							/**
							 * [tFilterRow_3 process_data_begin ] stop
							 */
// Start of branch "row8"
							if (row8 != null) {

								/**
								 * [tLogRow_4 main ] start
								 */

								currentComponent = "tLogRow_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row8"

									);
								}

///////////////////////		

								strBuffer_tLogRow_4 = new StringBuilder();

								if (row8.code != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.code));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.current != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.current));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.team != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.team));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.team_gender != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.team_gender));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.country_code != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.country_code));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.country != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.country));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.country_long != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.country_long));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.discipline != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.discipline));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.disciplines_code != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.disciplines_code));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.events != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.events));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.athletes != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.athletes));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.coaches != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.coaches));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.athletes_codes != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.athletes_codes));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.num_athletes != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.num_athletes));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.coaches_codes != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.coaches_codes));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row8.num_coaches != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row8.num_coaches));

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

							} // End of branch "row8"

// Start of branch "row9"
							if (row9 != null) {

								/**
								 * [tLogRow_5 main ] start
								 */

								currentComponent = "tLogRow_5";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row9"

									);
								}

///////////////////////		

								strBuffer_tLogRow_5 = new StringBuilder();

								if (row9.code != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.code));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.current != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.current));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.team != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.team));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.team_gender != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.team_gender));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.country_code != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.country_code));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.country != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.country));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.country_long != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.country_long));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.discipline != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.discipline));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.disciplines_code != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.disciplines_code));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.events != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.events));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.athletes != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.athletes));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.coaches != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.coaches));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.athletes_codes != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.athletes_codes));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.num_athletes != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.num_athletes));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.coaches_codes != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.coaches_codes));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.num_coaches != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.num_coaches));

								} //

								strBuffer_tLogRow_5.append("|");

								if (row9.errorMessage != null) { //

									strBuffer_tLogRow_5.append(String.valueOf(row9.errorMessage));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_5 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_5 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_5);
								}
								consoleOut_tLogRow_5.println(strBuffer_tLogRow_5.toString());
								consoleOut_tLogRow_5.flush();
								nb_line_tLogRow_5++;
//////

//////                    

///////////////////////    			

								tos_count_tLogRow_5++;

								/**
								 * [tLogRow_5 main ] stop
								 */

								/**
								 * [tLogRow_5 process_data_begin ] start
								 */

								currentComponent = "tLogRow_5";

								/**
								 * [tLogRow_5 process_data_begin ] stop
								 */

								/**
								 * [tLogRow_5 process_data_end ] start
								 */

								currentComponent = "tLogRow_5";

								/**
								 * [tLogRow_5 process_data_end ] stop
								 */

							} // End of branch "row9"

							/**
							 * [tFilterRow_3 process_data_end ] start
							 */

							currentComponent = "tFilterRow_3";

							/**
							 * [tFilterRow_3 process_data_end ] stop
							 */

						} // End of branch "row7"

						/**
						 * [tFileInputDelimited_3 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_3 end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						nb_line_tFileInputDelimited_3++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_3 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_3 != null) {
							csvReadertFileInputDelimited_3.close();
						}
					}
					if (csvReadertFileInputDelimited_3 != null) {
						globalMap.put("tFileInputDelimited_3_NB_LINE", nb_line_tFileInputDelimited_3);
					}

				}

				ok_Hash.put("tFileInputDelimited_3", true);
				end_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_3 end ] stop
				 */

				/**
				 * [tFilterRow_3 end ] start
				 */

				currentComponent = "tFilterRow_3";

				globalMap.put("tFilterRow_3_NB_LINE", nb_line_tFilterRow_3);
				globalMap.put("tFilterRow_3_NB_LINE_OK", nb_line_ok_tFilterRow_3);
				globalMap.put("tFilterRow_3_NB_LINE_REJECT", nb_line_reject_tFilterRow_3);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
				}

				ok_Hash.put("tFilterRow_3", true);
				end_Hash.put("tFilterRow_3", System.currentTimeMillis());

				/**
				 * [tFilterRow_3 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row8");
				}

				ok_Hash.put("tLogRow_4", true);
				end_Hash.put("tLogRow_4", System.currentTimeMillis());

				/**
				 * [tLogRow_4 end ] stop
				 */

				/**
				 * [tLogRow_5 end ] start
				 */

				currentComponent = "tLogRow_5";

//////
//////
				globalMap.put("tLogRow_5_NB_LINE", nb_line_tLogRow_5);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row9");
				}

				ok_Hash.put("tLogRow_5", true);
				end_Hash.put("tLogRow_5", System.currentTimeMillis());

				/**
				 * [tLogRow_5 end ] stop
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
				 * [tFileInputDelimited_3 finally ] start
				 */

				currentComponent = "tFileInputDelimited_3";

				/**
				 * [tFileInputDelimited_3 finally ] stop
				 */

				/**
				 * [tFilterRow_3 finally ] start
				 */

				currentComponent = "tFilterRow_3";

				/**
				 * [tFilterRow_3 finally ] stop
				 */

				/**
				 * [tLogRow_4 finally ] start
				 */

				currentComponent = "tLogRow_4";

				/**
				 * [tLogRow_4 finally ] stop
				 */

				/**
				 * [tLogRow_5 finally ] start
				 */

				currentComponent = "tLogRow_5";

				/**
				 * [tLogRow_5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 1);
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
		final completude_teams completude_teamsClass = new completude_teams();

		int exitCode = completude_teamsClass.runJobInTOS(args);

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
			java.io.InputStream inContext = completude_teams.class.getClassLoader()
					.getResourceAsStream("projettalend1/completude_teams_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = completude_teams.class.getClassLoader()
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
		try {
			errorCode = null;
			tFileInputDelimited_3Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_3) {
			globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_3.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : completude_teams");
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
 * 263344 characters generated by Talend Open Studio for Data Integration on the
 * 3 janvier 2025 à 10:23:15 CET
 ************************************************************************************************/