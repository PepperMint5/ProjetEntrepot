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

package projettalend1.completude_athletes_0_1;

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
 * Job: completude_athletes Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class completude_athletes implements TalendJob {

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
	private final String jobName = "completude_athletes";
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
					completude_athletes.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(completude_athletes.this, new Object[] { e, currentComponent, globalMap });
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

	public void tLogRow_2_error(Exception exception, String errorComponent,
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

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_3_error(Exception exception, String errorComponent,
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

	public void tFileInputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_3_error(Exception exception, String errorComponent,
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_athletes = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_athletes = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
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

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public Float height;

		public Float getHeight() {
			return this.height;
		}

		public Float weight;

		public Float getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + String.valueOf(height));
			sb.append(",weight=" + String.valueOf(weight));
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_athletes = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_athletes = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
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

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public Float height;

		public Float getHeight() {
			return this.height;
		}

		public Float weight;

		public Float getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

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
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + String.valueOf(height));
			sb.append(",weight=" + String.valueOf(weight));
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_athletes = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_athletes = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
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

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public Float height;

		public Float getHeight() {
			return this.height;
		}

		public Float weight;

		public Float getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + String.valueOf(height));
			sb.append(",weight=" + String.valueOf(weight));
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
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
				 * [tLogRow_2 begin ] start
				 */

				ok_Hash.put("tLogRow_2", false);
				start_Hash.put("tLogRow_2", System.currentTimeMillis());

				currentComponent = "tLogRow_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
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
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
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
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/athletes.csv"/**
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

								row1.name = null;

								row1.name_short = null;

								row1.name_tv = null;

								row1.gender = null;

								row1.function = null;

								row1.country_code = null;

								row1.country = null;

								row1.country_long = null;

								row1.nationality = null;

								row1.nationality_long = null;

								row1.nationality_code = null;

								row1.height = null;

								row1.weight = null;

								row1.disciplines = null;

								row1.events = null;

								row1.birth_date = null;

								row1.birth_place = null;

								row1.birth_country = null;

								row1.residence_place = null;

								row1.residence_country = null;

								row1.nickname = null;

								row1.hobbies = null;

								row1.occupation = null;

								row1.education = null;

								row1.family = null;

								row1.lang = null;

								row1.coach = null;

								row1.reason = null;

								row1.hero = null;

								row1.influence = null;

								row1.philosophy = null;

								row1.sporting_relatives = null;

								row1.ritual = null;

								row1.other_sports = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_1 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_1 = 0;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row1.code = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]);

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"code", "row1",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row1.code = null;

									}

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

									row1.name = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.name = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 3;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.name_short = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.name_short = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 4;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.name_tv = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.name_tv = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 5;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.gender = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.gender = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 6;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.function = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.function = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 7;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country_code = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country_code = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 8;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 9;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.country_long = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 10;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.nationality = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.nationality = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 11;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.nationality_long = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.nationality_long = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 12;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.nationality_code = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.nationality_code = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 13;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row1.height = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]);

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"height", "row1",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row1.height = null;

									}

								} else {

									row1.height = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 14;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row1.weight = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]);

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"weight", "row1",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row1.weight = null;

									}

								} else {

									row1.weight = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 15;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.disciplines = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.disciplines = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 16;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.events = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.events = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 17;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row1.birth_date = ParserUtils.parseTo_Date(
													rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
													"yyyy-mm-dd");

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"birth_date", "row1",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row1.birth_date = null;

									}

								} else {

									row1.birth_date = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 18;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.birth_place = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.birth_place = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 19;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.birth_country = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.birth_country = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 20;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.residence_place = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.residence_place = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 21;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.residence_country = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.residence_country = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 22;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.nickname = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.nickname = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 23;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.hobbies = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.hobbies = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 24;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.occupation = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.occupation = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 25;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.education = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.education = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 26;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.family = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.family = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 27;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.lang = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.lang = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 28;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.coach = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.coach = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 29;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.reason = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.reason = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 30;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.hero = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.hero = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 31;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.influence = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.influence = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 32;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.philosophy = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.philosophy = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 33;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.sporting_relatives = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.sporting_relatives = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 34;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.ritual = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.ritual = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 35;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.other_sports = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.other_sports = null;

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
							Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
							ope_tFilterRow_1
									.matches(
											(row1.height == null ? false
													: row1.height.compareTo(
															ParserUtils.parseTo_Float(String.valueOf(0))) > 0),
											"height.compareTo(0) > 0 failed");

							ope_tFilterRow_1
									.matches(
											(row1.weight == null ? false
													: row1.weight.compareTo(
															ParserUtils.parseTo_Float(String.valueOf(0))) > 0),
											"weight.compareTo(0) > 0 failed");

							if (ope_tFilterRow_1.getMatchFlag()) {
								if (row2 == null) {
									row2 = new row2Struct();
								}
								row2.code = row1.code;
								row2.current = row1.current;
								row2.name = row1.name;
								row2.name_short = row1.name_short;
								row2.name_tv = row1.name_tv;
								row2.gender = row1.gender;
								row2.function = row1.function;
								row2.country_code = row1.country_code;
								row2.country = row1.country;
								row2.country_long = row1.country_long;
								row2.nationality = row1.nationality;
								row2.nationality_long = row1.nationality_long;
								row2.nationality_code = row1.nationality_code;
								row2.height = row1.height;
								row2.weight = row1.weight;
								row2.disciplines = row1.disciplines;
								row2.events = row1.events;
								row2.birth_date = row1.birth_date;
								row2.birth_place = row1.birth_place;
								row2.birth_country = row1.birth_country;
								row2.residence_place = row1.residence_place;
								row2.residence_country = row1.residence_country;
								row2.nickname = row1.nickname;
								row2.hobbies = row1.hobbies;
								row2.occupation = row1.occupation;
								row2.education = row1.education;
								row2.family = row1.family;
								row2.lang = row1.lang;
								row2.coach = row1.coach;
								row2.reason = row1.reason;
								row2.hero = row1.hero;
								row2.influence = row1.influence;
								row2.philosophy = row1.philosophy;
								row2.sporting_relatives = row1.sporting_relatives;
								row2.ritual = row1.ritual;
								row2.other_sports = row1.other_sports;
								nb_line_ok_tFilterRow_1++;
							} else {
								if (row3 == null) {
									row3 = new row3Struct();
								}
								row3.code = row1.code;
								row3.current = row1.current;
								row3.name = row1.name;
								row3.name_short = row1.name_short;
								row3.name_tv = row1.name_tv;
								row3.gender = row1.gender;
								row3.function = row1.function;
								row3.country_code = row1.country_code;
								row3.country = row1.country;
								row3.country_long = row1.country_long;
								row3.nationality = row1.nationality;
								row3.nationality_long = row1.nationality_long;
								row3.nationality_code = row1.nationality_code;
								row3.height = row1.height;
								row3.weight = row1.weight;
								row3.disciplines = row1.disciplines;
								row3.events = row1.events;
								row3.birth_date = row1.birth_date;
								row3.birth_place = row1.birth_place;
								row3.birth_country = row1.birth_country;
								row3.residence_place = row1.residence_place;
								row3.residence_country = row1.residence_country;
								row3.nickname = row1.nickname;
								row3.hobbies = row1.hobbies;
								row3.occupation = row1.occupation;
								row3.education = row1.education;
								row3.family = row1.family;
								row3.lang = row1.lang;
								row3.coach = row1.coach;
								row3.reason = row1.reason;
								row3.hero = row1.hero;
								row3.influence = row1.influence;
								row3.philosophy = row1.philosophy;
								row3.sporting_relatives = row1.sporting_relatives;
								row3.ritual = row1.ritual;
								row3.other_sports = row1.other_sports;
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
								 * [tLogRow_2 main ] start
								 */

								currentComponent = "tLogRow_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

///////////////////////		

								strBuffer_tLogRow_2 = new StringBuilder();

								if (row2.code != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.code));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.current != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.current));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.name != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.name));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.name_short != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.name_short));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.name_tv != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.name_tv));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.gender != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.gender));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.function != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.function));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.country_code != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.country_code));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.country != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.country));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.country_long != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.country_long));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.nationality != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.nationality));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.nationality_long != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.nationality_long));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.nationality_code != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.nationality_code));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.height != null) { //

									strBuffer_tLogRow_2.append(FormatterUtils.formatUnwithE(row2.height));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.weight != null) { //

									strBuffer_tLogRow_2.append(FormatterUtils.formatUnwithE(row2.weight));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.disciplines != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.disciplines));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.events != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.events));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.birth_date != null) { //

									strBuffer_tLogRow_2
											.append(FormatterUtils.format_Date(row2.birth_date, "yyyy-mm-dd"));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.birth_place != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.birth_place));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.birth_country != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.birth_country));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.residence_place != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.residence_place));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.residence_country != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.residence_country));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.nickname != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.nickname));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.hobbies != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.hobbies));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.occupation != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.occupation));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.education != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.education));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.family != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.family));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.lang != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.lang));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.coach != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.coach));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.reason != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.reason));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.hero != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.hero));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.influence != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.influence));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.philosophy != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.philosophy));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.sporting_relatives != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.sporting_relatives));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.ritual != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.ritual));

								} //

								strBuffer_tLogRow_2.append("|");

								if (row2.other_sports != null) { //

									strBuffer_tLogRow_2.append(String.valueOf(row2.other_sports));

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

							} // End of branch "row2"

// Start of branch "row3"
							if (row3 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row3"

									);
								}

///////////////////////		

								strBuffer_tLogRow_1 = new StringBuilder();

								if (row3.code != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.code));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.current != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.current));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.name != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.name));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.name_short != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.name_short));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.name_tv != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.name_tv));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.gender != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.gender));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.function != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.function));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.country_code != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.country_code));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.country != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.country));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.country_long != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.country_long));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.nationality != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.nationality));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.nationality_long != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.nationality_long));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.nationality_code != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.nationality_code));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.height != null) { //

									strBuffer_tLogRow_1.append(FormatterUtils.formatUnwithE(row3.height));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.weight != null) { //

									strBuffer_tLogRow_1.append(FormatterUtils.formatUnwithE(row3.weight));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.disciplines != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.disciplines));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.events != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.events));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.birth_date != null) { //

									strBuffer_tLogRow_1
											.append(FormatterUtils.format_Date(row3.birth_date, "yyyy-mm-dd"));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.birth_place != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.birth_place));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.birth_country != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.birth_country));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.residence_place != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.residence_place));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.residence_country != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.residence_country));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.nickname != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.nickname));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.hobbies != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.hobbies));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.occupation != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.occupation));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.education != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.education));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.family != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.family));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.lang != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.lang));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.coach != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.coach));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.reason != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.reason));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.hero != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.hero));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.influence != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.influence));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.philosophy != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.philosophy));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.sporting_relatives != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.sporting_relatives));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.ritual != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.ritual));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.other_sports != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.other_sports));

								} //

								strBuffer_tLogRow_1.append("|");

								if (row3.errorMessage != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(row3.errorMessage));

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
				 * [tLogRow_2 end ] start
				 */

				currentComponent = "tLogRow_2";

//////
//////
				globalMap.put("tLogRow_2_NB_LINE", nb_line_tLogRow_2);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tLogRow_2", true);
				end_Hash.put("tLogRow_2", System.currentTimeMillis());

				/**
				 * [tLogRow_2 end ] stop
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
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
				 * [tLogRow_2 finally ] start
				 */

				currentComponent = "tLogRow_2";

				/**
				 * [tLogRow_2 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_athletes = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_athletes = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
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

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public Float height;

		public Float getHeight() {
			return this.height;
		}

		public Float weight;

		public Float getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + String.valueOf(height));
			sb.append(",weight=" + String.valueOf(weight));
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_athletes = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_athletes = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
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

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public Float height;

		public Float getHeight() {
			return this.height;
		}

		public Float weight;

		public Float getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

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
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + String.valueOf(height));
			sb.append(",weight=" + String.valueOf(weight));
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_athletes = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_athletes = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
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

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public Float height;

		public Float getHeight() {
			return this.height;
		}

		public Float weight;

		public Float getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + String.valueOf(height));
			sb.append(",weight=" + String.valueOf(weight));
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
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
				 * [tFilterRow_3 begin ] start
				 */

				ok_Hash.put("tFilterRow_3", false);
				start_Hash.put("tFilterRow_3", System.currentTimeMillis());

				currentComponent = "tFilterRow_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
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
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/athletes.csv"/**
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

								row4.name = null;

								row4.name_short = null;

								row4.name_tv = null;

								row4.gender = null;

								row4.function = null;

								row4.country_code = null;

								row4.country = null;

								row4.country_long = null;

								row4.nationality = null;

								row4.nationality_long = null;

								row4.nationality_code = null;

								row4.height = null;

								row4.weight = null;

								row4.disciplines = null;

								row4.events = null;

								row4.birth_date = null;

								row4.birth_place = null;

								row4.birth_country = null;

								row4.residence_place = null;

								row4.residence_country = null;

								row4.nickname = null;

								row4.hobbies = null;

								row4.occupation = null;

								row4.education = null;

								row4.family = null;

								row4.lang = null;

								row4.coach = null;

								row4.reason = null;

								row4.hero = null;

								row4.influence = null;

								row4.philosophy = null;

								row4.sporting_relatives = null;

								row4.ritual = null;

								row4.other_sports = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_2 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_2 = 0;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row4.code = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2]);

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"code", "row4",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row4.code = null;

									}

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

									row4.name = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.name = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 3;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.name_short = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.name_short = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 4;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.name_tv = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.name_tv = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 5;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.gender = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.gender = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 6;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.function = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.function = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 7;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.country_code = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.country_code = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 8;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.country = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.country = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 9;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.country_long = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 10;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.nationality = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.nationality = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 11;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.nationality_long = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.nationality_long = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 12;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.nationality_code = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.nationality_code = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 13;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row4.height = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2]);

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"height", "row4",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row4.height = null;

									}

								} else {

									row4.height = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 14;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row4.weight = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2]);

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"weight", "row4",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row4.weight = null;

									}

								} else {

									row4.weight = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 15;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.disciplines = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.disciplines = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 16;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.events = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.events = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 17;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row4.birth_date = ParserUtils.parseTo_Date(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
													"yyyy-mm-dd");

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"birth_date", "row4",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row4.birth_date = null;

									}

								} else {

									row4.birth_date = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 18;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.birth_place = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.birth_place = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 19;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.birth_country = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.birth_country = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 20;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.residence_place = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.residence_place = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 21;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.residence_country = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.residence_country = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 22;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.nickname = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.nickname = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 23;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.hobbies = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.hobbies = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 24;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.occupation = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.occupation = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 25;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.education = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.education = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 26;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.family = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.family = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 27;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.lang = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.lang = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 28;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.coach = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.coach = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 29;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.reason = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.reason = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 30;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.hero = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.hero = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 31;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.influence = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.influence = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 32;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.philosophy = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.philosophy = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 33;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.sporting_relatives = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.sporting_relatives = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 34;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.ritual = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.ritual = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 35;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row4.other_sports = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row4.other_sports = null;

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
							 * [tFilterRow_3 main ] start
							 */

							currentComponent = "tFilterRow_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row4"

								);
							}

							row6 = null;
							row5 = null;
							Operator_tFilterRow_3 ope_tFilterRow_3 = new Operator_tFilterRow_3("&&");
							ope_tFilterRow_3.matches(
									(row4.nationality == null ? false : row4.nationality.compareTo("") != 0),
									"nationality.compareTo(\"\") != 0 failed");

							if (ope_tFilterRow_3.getMatchFlag()) {
								if (row5 == null) {
									row5 = new row5Struct();
								}
								row5.code = row4.code;
								row5.current = row4.current;
								row5.name = row4.name;
								row5.name_short = row4.name_short;
								row5.name_tv = row4.name_tv;
								row5.gender = row4.gender;
								row5.function = row4.function;
								row5.country_code = row4.country_code;
								row5.country = row4.country;
								row5.country_long = row4.country_long;
								row5.nationality = row4.nationality;
								row5.nationality_long = row4.nationality_long;
								row5.nationality_code = row4.nationality_code;
								row5.height = row4.height;
								row5.weight = row4.weight;
								row5.disciplines = row4.disciplines;
								row5.events = row4.events;
								row5.birth_date = row4.birth_date;
								row5.birth_place = row4.birth_place;
								row5.birth_country = row4.birth_country;
								row5.residence_place = row4.residence_place;
								row5.residence_country = row4.residence_country;
								row5.nickname = row4.nickname;
								row5.hobbies = row4.hobbies;
								row5.occupation = row4.occupation;
								row5.education = row4.education;
								row5.family = row4.family;
								row5.lang = row4.lang;
								row5.coach = row4.coach;
								row5.reason = row4.reason;
								row5.hero = row4.hero;
								row5.influence = row4.influence;
								row5.philosophy = row4.philosophy;
								row5.sporting_relatives = row4.sporting_relatives;
								row5.ritual = row4.ritual;
								row5.other_sports = row4.other_sports;
								nb_line_ok_tFilterRow_3++;
							} else {
								if (row6 == null) {
									row6 = new row6Struct();
								}
								row6.code = row4.code;
								row6.current = row4.current;
								row6.name = row4.name;
								row6.name_short = row4.name_short;
								row6.name_tv = row4.name_tv;
								row6.gender = row4.gender;
								row6.function = row4.function;
								row6.country_code = row4.country_code;
								row6.country = row4.country;
								row6.country_long = row4.country_long;
								row6.nationality = row4.nationality;
								row6.nationality_long = row4.nationality_long;
								row6.nationality_code = row4.nationality_code;
								row6.height = row4.height;
								row6.weight = row4.weight;
								row6.disciplines = row4.disciplines;
								row6.events = row4.events;
								row6.birth_date = row4.birth_date;
								row6.birth_place = row4.birth_place;
								row6.birth_country = row4.birth_country;
								row6.residence_place = row4.residence_place;
								row6.residence_country = row4.residence_country;
								row6.nickname = row4.nickname;
								row6.hobbies = row4.hobbies;
								row6.occupation = row4.occupation;
								row6.education = row4.education;
								row6.family = row4.family;
								row6.lang = row4.lang;
								row6.coach = row4.coach;
								row6.reason = row4.reason;
								row6.hero = row4.hero;
								row6.influence = row4.influence;
								row6.philosophy = row4.philosophy;
								row6.sporting_relatives = row4.sporting_relatives;
								row6.ritual = row4.ritual;
								row6.other_sports = row4.other_sports;
								row6.errorMessage = ope_tFilterRow_3.getErrorMsg();
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

								if (row5.name != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.name));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.name_short != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.name_short));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.name_tv != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.name_tv));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.gender != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.gender));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.function != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.function));

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

								if (row5.nationality != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.nationality));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.nationality_long != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.nationality_long));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.nationality_code != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.nationality_code));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.height != null) { //

									strBuffer_tLogRow_3.append(FormatterUtils.formatUnwithE(row5.height));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.weight != null) { //

									strBuffer_tLogRow_3.append(FormatterUtils.formatUnwithE(row5.weight));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.disciplines != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.disciplines));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.events != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.events));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.birth_date != null) { //

									strBuffer_tLogRow_3
											.append(FormatterUtils.format_Date(row5.birth_date, "yyyy-mm-dd"));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.birth_place != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.birth_place));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.birth_country != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.birth_country));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.residence_place != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.residence_place));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.residence_country != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.residence_country));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.nickname != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.nickname));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.hobbies != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.hobbies));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.occupation != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.occupation));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.education != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.education));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.family != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.family));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.lang != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.lang));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.coach != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.coach));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.reason != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.reason));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.hero != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.hero));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.influence != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.influence));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.philosophy != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.philosophy));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.sporting_relatives != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.sporting_relatives));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.ritual != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.ritual));

								} //

								strBuffer_tLogRow_3.append("|");

								if (row5.other_sports != null) { //

									strBuffer_tLogRow_3.append(String.valueOf(row5.other_sports));

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

								if (row6.code != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.code));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.current != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.current));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.name != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.name));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.name_short != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.name_short));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.name_tv != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.name_tv));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.gender != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.gender));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.function != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.function));

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

								strBuffer_tLogRow_4.append("|");

								if (row6.nationality != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.nationality));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.nationality_long != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.nationality_long));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.nationality_code != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.nationality_code));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.height != null) { //

									strBuffer_tLogRow_4.append(FormatterUtils.formatUnwithE(row6.height));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.weight != null) { //

									strBuffer_tLogRow_4.append(FormatterUtils.formatUnwithE(row6.weight));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.disciplines != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.disciplines));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.events != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.events));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.birth_date != null) { //

									strBuffer_tLogRow_4
											.append(FormatterUtils.format_Date(row6.birth_date, "yyyy-mm-dd"));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.birth_place != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.birth_place));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.birth_country != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.birth_country));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.residence_place != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.residence_place));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.residence_country != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.residence_country));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.nickname != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.nickname));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.hobbies != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.hobbies));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.occupation != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.occupation));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.education != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.education));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.family != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.family));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.lang != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.lang));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.coach != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.coach));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.reason != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.reason));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.hero != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.hero));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.influence != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.influence));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.philosophy != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.philosophy));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.sporting_relatives != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.sporting_relatives));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.ritual != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.ritual));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.other_sports != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.other_sports));

								} //

								strBuffer_tLogRow_4.append("|");

								if (row6.errorMessage != null) { //

									strBuffer_tLogRow_4.append(String.valueOf(row6.errorMessage));

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
							 * [tFilterRow_3 process_data_end ] start
							 */

							currentComponent = "tFilterRow_3";

							/**
							 * [tFilterRow_3 process_data_end ] stop
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
				 * [tFilterRow_3 end ] start
				 */

				currentComponent = "tFilterRow_3";

				globalMap.put("tFilterRow_3_NB_LINE", nb_line_tFilterRow_3);
				globalMap.put("tFilterRow_3_NB_LINE_OK", nb_line_ok_tFilterRow_3);
				globalMap.put("tFilterRow_3_NB_LINE_REJECT", nb_line_reject_tFilterRow_3);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tFilterRow_3", true);
				end_Hash.put("tFilterRow_3", System.currentTimeMillis());

				/**
				 * [tFilterRow_3 end ] stop
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
				 * [tFilterRow_3 finally ] start
				 */

				currentComponent = "tFilterRow_3";

				/**
				 * [tFilterRow_3 finally ] stop
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

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_athletes = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_athletes = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
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

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public String height;

		public String getHeight() {
			return this.height;
		}

		public String weight;

		public String getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					this.height = readString(dis);

					this.weight = readString(dis);

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					this.height = readString(dis);

					this.weight = readString(dis);

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// String

				writeString(this.height, dos);

				// String

				writeString(this.weight, dos);

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// String

				writeString(this.height, dos);

				// String

				writeString(this.weight, dos);

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + height);
			sb.append(",weight=" + weight);
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

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
		final static byte[] commonByteArrayLock_PROJETTALEND1_completude_athletes = new byte[0];
		static byte[] commonByteArray_PROJETTALEND1_completude_athletes = new byte[0];

		public Integer code;

		public Integer getCode() {
			return this.code;
		}

		public String current;

		public String getCurrent() {
			return this.current;
		}

		public String name;

		public String getName() {
			return this.name;
		}

		public String name_short;

		public String getName_short() {
			return this.name_short;
		}

		public String name_tv;

		public String getName_tv() {
			return this.name_tv;
		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public String function;

		public String getFunction() {
			return this.function;
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

		public String nationality;

		public String getNationality() {
			return this.nationality;
		}

		public String nationality_long;

		public String getNationality_long() {
			return this.nationality_long;
		}

		public String nationality_code;

		public String getNationality_code() {
			return this.nationality_code;
		}

		public Float height;

		public Float getHeight() {
			return this.height;
		}

		public Float weight;

		public Float getWeight() {
			return this.weight;
		}

		public String disciplines;

		public String getDisciplines() {
			return this.disciplines;
		}

		public String events;

		public String getEvents() {
			return this.events;
		}

		public java.util.Date birth_date;

		public java.util.Date getBirth_date() {
			return this.birth_date;
		}

		public String birth_place;

		public String getBirth_place() {
			return this.birth_place;
		}

		public String birth_country;

		public String getBirth_country() {
			return this.birth_country;
		}

		public String residence_place;

		public String getResidence_place() {
			return this.residence_place;
		}

		public String residence_country;

		public String getResidence_country() {
			return this.residence_country;
		}

		public String nickname;

		public String getNickname() {
			return this.nickname;
		}

		public String hobbies;

		public String getHobbies() {
			return this.hobbies;
		}

		public String occupation;

		public String getOccupation() {
			return this.occupation;
		}

		public String education;

		public String getEducation() {
			return this.education;
		}

		public String family;

		public String getFamily() {
			return this.family;
		}

		public String lang;

		public String getLang() {
			return this.lang;
		}

		public String coach;

		public String getCoach() {
			return this.coach;
		}

		public String reason;

		public String getReason() {
			return this.reason;
		}

		public String hero;

		public String getHero() {
			return this.hero;
		}

		public String influence;

		public String getInfluence() {
			return this.influence;
		}

		public String philosophy;

		public String getPhilosophy() {
			return this.philosophy;
		}

		public String sporting_relatives;

		public String getSporting_relatives() {
			return this.sporting_relatives;
		}

		public String ritual;

		public String getRitual() {
			return this.ritual;
		}

		public String other_sports;

		public String getOther_sports() {
			return this.other_sports;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJETTALEND1_completude_athletes.length) {
					if (length < 1024 && commonByteArray_PROJETTALEND1_completude_athletes.length == 0) {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[1024];
					} else {
						commonByteArray_PROJETTALEND1_completude_athletes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJETTALEND1_completude_athletes, 0, length);
				strReturn = new String(commonByteArray_PROJETTALEND1_completude_athletes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJETTALEND1_completude_athletes) {

				try {

					int length = 0;

					this.code = readInteger(dis);

					this.current = readString(dis);

					this.name = readString(dis);

					this.name_short = readString(dis);

					this.name_tv = readString(dis);

					this.gender = readString(dis);

					this.function = readString(dis);

					this.country_code = readString(dis);

					this.country = readString(dis);

					this.country_long = readString(dis);

					this.nationality = readString(dis);

					this.nationality_long = readString(dis);

					this.nationality_code = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.height = null;
					} else {
						this.height = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.weight = null;
					} else {
						this.weight = dis.readFloat();
					}

					this.disciplines = readString(dis);

					this.events = readString(dis);

					this.birth_date = readDate(dis);

					this.birth_place = readString(dis);

					this.birth_country = readString(dis);

					this.residence_place = readString(dis);

					this.residence_country = readString(dis);

					this.nickname = readString(dis);

					this.hobbies = readString(dis);

					this.occupation = readString(dis);

					this.education = readString(dis);

					this.family = readString(dis);

					this.lang = readString(dis);

					this.coach = readString(dis);

					this.reason = readString(dis);

					this.hero = readString(dis);

					this.influence = readString(dis);

					this.philosophy = readString(dis);

					this.sporting_relatives = readString(dis);

					this.ritual = readString(dis);

					this.other_sports = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.code, dos);

				// String

				writeString(this.current, dos);

				// String

				writeString(this.name, dos);

				// String

				writeString(this.name_short, dos);

				// String

				writeString(this.name_tv, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.function, dos);

				// String

				writeString(this.country_code, dos);

				// String

				writeString(this.country, dos);

				// String

				writeString(this.country_long, dos);

				// String

				writeString(this.nationality, dos);

				// String

				writeString(this.nationality_long, dos);

				// String

				writeString(this.nationality_code, dos);

				// Float

				if (this.height == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.height);
				}

				// Float

				if (this.weight == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.weight);
				}

				// String

				writeString(this.disciplines, dos);

				// String

				writeString(this.events, dos);

				// java.util.Date

				writeDate(this.birth_date, dos);

				// String

				writeString(this.birth_place, dos);

				// String

				writeString(this.birth_country, dos);

				// String

				writeString(this.residence_place, dos);

				// String

				writeString(this.residence_country, dos);

				// String

				writeString(this.nickname, dos);

				// String

				writeString(this.hobbies, dos);

				// String

				writeString(this.occupation, dos);

				// String

				writeString(this.education, dos);

				// String

				writeString(this.family, dos);

				// String

				writeString(this.lang, dos);

				// String

				writeString(this.coach, dos);

				// String

				writeString(this.reason, dos);

				// String

				writeString(this.hero, dos);

				// String

				writeString(this.influence, dos);

				// String

				writeString(this.philosophy, dos);

				// String

				writeString(this.sporting_relatives, dos);

				// String

				writeString(this.ritual, dos);

				// String

				writeString(this.other_sports, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("code=" + String.valueOf(code));
			sb.append(",current=" + current);
			sb.append(",name=" + name);
			sb.append(",name_short=" + name_short);
			sb.append(",name_tv=" + name_tv);
			sb.append(",gender=" + gender);
			sb.append(",function=" + function);
			sb.append(",country_code=" + country_code);
			sb.append(",country=" + country);
			sb.append(",country_long=" + country_long);
			sb.append(",nationality=" + nationality);
			sb.append(",nationality_long=" + nationality_long);
			sb.append(",nationality_code=" + nationality_code);
			sb.append(",height=" + String.valueOf(height));
			sb.append(",weight=" + String.valueOf(weight));
			sb.append(",disciplines=" + disciplines);
			sb.append(",events=" + events);
			sb.append(",birth_date=" + String.valueOf(birth_date));
			sb.append(",birth_place=" + birth_place);
			sb.append(",birth_country=" + birth_country);
			sb.append(",residence_place=" + residence_place);
			sb.append(",residence_country=" + residence_country);
			sb.append(",nickname=" + nickname);
			sb.append(",hobbies=" + hobbies);
			sb.append(",occupation=" + occupation);
			sb.append(",education=" + education);
			sb.append(",family=" + family);
			sb.append(",lang=" + lang);
			sb.append(",coach=" + coach);
			sb.append(",reason=" + reason);
			sb.append(",hero=" + hero);
			sb.append(",influence=" + influence);
			sb.append(",philosophy=" + philosophy);
			sb.append(",sporting_relatives=" + sporting_relatives);
			sb.append(",ritual=" + ritual);
			sb.append(",other_sports=" + other_sports);
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
				out1Struct out1 = new out1Struct();

				/**
				 * [tFileOutputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_3", false);
				start_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out1");
				}

				int tos_count_tFileOutputDelimited_3 = 0;

				String fileName_tFileOutputDelimited_3 = "";
				fileName_tFileOutputDelimited_3 = (new java.io.File(
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/ProjetEntrepot/output/cleanProcess/athletes_cleaned.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_3 = null;
				String extension_tFileOutputDelimited_3 = null;
				String directory_tFileOutputDelimited_3 = null;
				if ((fileName_tFileOutputDelimited_3.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") < fileName_tFileOutputDelimited_3
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
							fileName_tFileOutputDelimited_3.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					}
					directory_tFileOutputDelimited_3 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_3 = true;
				java.io.File filetFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				globalMap.put("tFileOutputDelimited_3_FILE_NAME", fileName_tFileOutputDelimited_3);
				if (filetFileOutputDelimited_3.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_3.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				String[] headColutFileOutputDelimited_3 = new String[36];
				class CSVBasicSet_tFileOutputDelimited_3 {
					private char field_Delim;
					private char row_Delim;
					private char escape;
					private char textEnclosure;
					private boolean useCRLFRecordDelimiter;

					public boolean isUseCRLFRecordDelimiter() {
						return useCRLFRecordDelimiter;
					}

					public void setFieldSeparator(String fieldSep) throws IllegalArgumentException {
						char field_Delim_tFileOutputDelimited_3[] = null;

						// support passing value (property: Field Separator) by 'context.fs' or
						// 'globalMap.get("fs")'.
						if (fieldSep.length() > 0) {
							field_Delim_tFileOutputDelimited_3 = fieldSep.toCharArray();
						} else {
							throw new IllegalArgumentException("Field Separator must be assigned a char.");
						}
						this.field_Delim = field_Delim_tFileOutputDelimited_3[0];
					}

					public char getFieldDelim() {
						if (this.field_Delim == 0) {
							setFieldSeparator(";");
						}
						return this.field_Delim;
					}

					public void setRowSeparator(String rowSep) {
						if ("\r\n".equals(rowSep)) {
							useCRLFRecordDelimiter = true;
							return;
						}
						char row_DelimtFileOutputDelimited_3[] = null;

						// support passing value (property: Row Separator) by 'context.rs' or
						// 'globalMap.get("rs")'.
						if (rowSep.length() > 0) {
							row_DelimtFileOutputDelimited_3 = rowSep.toCharArray();
						} else {
							throw new IllegalArgumentException("Row Separator must be assigned a char.");
						}
						this.row_Delim = row_DelimtFileOutputDelimited_3[0];
					}

					public char getRowDelim() {
						if (this.row_Delim == 0) {
							setRowSeparator("\n");
						}
						return this.row_Delim;
					}

					public void setEscapeAndTextEnclosure(String strEscape, String strTextEnclosure)
							throws IllegalArgumentException {
						if (strEscape.length() <= 0) {
							throw new IllegalArgumentException("Escape Char must be assigned a char.");
						}

						if ("".equals(strTextEnclosure))
							strTextEnclosure = "\0";
						char textEnclosure_tFileOutputDelimited_3[] = null;

						if (strTextEnclosure.length() > 0) {
							textEnclosure_tFileOutputDelimited_3 = strTextEnclosure.toCharArray();
						} else {
							throw new IllegalArgumentException("Text Enclosure must be assigned a char.");
						}

						this.textEnclosure = textEnclosure_tFileOutputDelimited_3[0];

						if (("\\").equals(strEscape)) {
							this.escape = '\\';
						} else if (strEscape.equals(strTextEnclosure)) {
							this.escape = this.textEnclosure;
						} else {
							// the default escape mode is double escape
							this.escape = this.textEnclosure;
						}

					}

					public char getEscapeChar() {
						return (char) this.escape;
					}

					public char getTextEnclosure() {
						return this.textEnclosure;
					}
				}

				int nb_line_tFileOutputDelimited_3 = 0;
				int splitedFileNo_tFileOutputDelimited_3 = 0;
				int currentRow_tFileOutputDelimited_3 = 0;

				CSVBasicSet_tFileOutputDelimited_3 csvSettings_tFileOutputDelimited_3 = new CSVBasicSet_tFileOutputDelimited_3();
				csvSettings_tFileOutputDelimited_3.setFieldSeparator(";");
				csvSettings_tFileOutputDelimited_3.setRowSeparator("\n");
				csvSettings_tFileOutputDelimited_3.setEscapeAndTextEnclosure("\"", "\"");
				// create directory only if not exists
				if (directory_tFileOutputDelimited_3 != null && directory_tFileOutputDelimited_3.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_3 = new java.io.File(directory_tFileOutputDelimited_3);
					if (!dir_tFileOutputDelimited_3.exists()) {
						dir_tFileOutputDelimited_3.mkdirs();
					}
				}
				com.talend.csv.CSVWriter CsvWritertFileOutputDelimited_3 = null;

				java.io.File fileToDelete_tFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				if (fileToDelete_tFileOutputDelimited_3.exists()) {
					fileToDelete_tFileOutputDelimited_3.delete();
				}
				CsvWritertFileOutputDelimited_3 = new com.talend.csv.CSVWriter(
						new java.io.BufferedWriter(new java.io.OutputStreamWriter(
								new java.io.FileOutputStream(fileName_tFileOutputDelimited_3, false), "ISO-8859-15")));
				CsvWritertFileOutputDelimited_3.setSeparator(csvSettings_tFileOutputDelimited_3.getFieldDelim());
				if (!csvSettings_tFileOutputDelimited_3.isUseCRLFRecordDelimiter()
						&& csvSettings_tFileOutputDelimited_3.getRowDelim() != '\r'
						&& csvSettings_tFileOutputDelimited_3.getRowDelim() != '\n') {
					CsvWritertFileOutputDelimited_3.setLineEnd("" + csvSettings_tFileOutputDelimited_3.getRowDelim());
				}
				if (filetFileOutputDelimited_3.length() == 0) {
					headColutFileOutputDelimited_3[0] = "code";
					headColutFileOutputDelimited_3[1] = "current";
					headColutFileOutputDelimited_3[2] = "name";
					headColutFileOutputDelimited_3[3] = "name_short";
					headColutFileOutputDelimited_3[4] = "name_tv";
					headColutFileOutputDelimited_3[5] = "gender";
					headColutFileOutputDelimited_3[6] = "function";
					headColutFileOutputDelimited_3[7] = "country_code";
					headColutFileOutputDelimited_3[8] = "country";
					headColutFileOutputDelimited_3[9] = "country_long";
					headColutFileOutputDelimited_3[10] = "nationality";
					headColutFileOutputDelimited_3[11] = "nationality_long";
					headColutFileOutputDelimited_3[12] = "nationality_code";
					headColutFileOutputDelimited_3[13] = "height";
					headColutFileOutputDelimited_3[14] = "weight";
					headColutFileOutputDelimited_3[15] = "disciplines";
					headColutFileOutputDelimited_3[16] = "events";
					headColutFileOutputDelimited_3[17] = "birth_date";
					headColutFileOutputDelimited_3[18] = "birth_place";
					headColutFileOutputDelimited_3[19] = "birth_country";
					headColutFileOutputDelimited_3[20] = "residence_place";
					headColutFileOutputDelimited_3[21] = "residence_country";
					headColutFileOutputDelimited_3[22] = "nickname";
					headColutFileOutputDelimited_3[23] = "hobbies";
					headColutFileOutputDelimited_3[24] = "occupation";
					headColutFileOutputDelimited_3[25] = "education";
					headColutFileOutputDelimited_3[26] = "family";
					headColutFileOutputDelimited_3[27] = "lang";
					headColutFileOutputDelimited_3[28] = "coach";
					headColutFileOutputDelimited_3[29] = "reason";
					headColutFileOutputDelimited_3[30] = "hero";
					headColutFileOutputDelimited_3[31] = "influence";
					headColutFileOutputDelimited_3[32] = "philosophy";
					headColutFileOutputDelimited_3[33] = "sporting_relatives";
					headColutFileOutputDelimited_3[34] = "ritual";
					headColutFileOutputDelimited_3[35] = "other_sports";
					CsvWritertFileOutputDelimited_3.writeNext(headColutFileOutputDelimited_3);
					CsvWritertFileOutputDelimited_3.flush();
				}
				CsvWritertFileOutputDelimited_3.setEscapeChar(csvSettings_tFileOutputDelimited_3.getEscapeChar());
				CsvWritertFileOutputDelimited_3.setQuoteChar(csvSettings_tFileOutputDelimited_3.getTextEnclosure());
				CsvWritertFileOutputDelimited_3.setQuoteStatus(com.talend.csv.CSVWriter.QuoteStatus.FORCE);

				resourceMap.put("CsvWriter_tFileOutputDelimited_3", CsvWritertFileOutputDelimited_3);
				resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

				/**
				 * [tFileOutputDelimited_3 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					String height;
					String weight;
					String nationality;
					String nationality_long;
					String nationality_code;
					String birth_place;
					String birth_country;
					String residence_place;
					String residence_country;
					String nickname;
					String hobbies;
					String occupation;
					String education;
					String family;
					String lang;
					String coach;
					String reason;
					String hero;
					String influence;
					String philosophy;
					String sporting_relatives;
					String other_sports;
					String ritual;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				out1Struct out1_tmp = new out1Struct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
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
						"C:/Users/Alix Lemoine/Documents/M2/S1/Entrepôt de Données/Projet/TP_Donnees_JO_Paris_2024/athletes.csv"/**
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

								row7.name = null;

								row7.name_short = null;

								row7.name_tv = null;

								row7.gender = null;

								row7.function = null;

								row7.country_code = null;

								row7.country = null;

								row7.country_long = null;

								row7.nationality = null;

								row7.nationality_long = null;

								row7.nationality_code = null;

								row7.height = null;

								row7.weight = null;

								row7.disciplines = null;

								row7.events = null;

								row7.birth_date = null;

								row7.birth_place = null;

								row7.birth_country = null;

								row7.residence_place = null;

								row7.residence_country = null;

								row7.nickname = null;

								row7.hobbies = null;

								row7.occupation = null;

								row7.education = null;

								row7.family = null;

								row7.lang = null;

								row7.coach = null;

								row7.reason = null;

								row7.hero = null;

								row7.influence = null;

								row7.philosophy = null;

								row7.sporting_relatives = null;

								row7.ritual = null;

								row7.other_sports = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_3 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_3 = 0;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									if (rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3].length() > 0) {
										try {

											row7.code = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3]);

										} catch (java.lang.Exception ex_tFileInputDelimited_3) {
											globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
													ex_tFileInputDelimited_3.getMessage());
											rowstate_tFileInputDelimited_3.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"code", "row7",
															rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
															ex_tFileInputDelimited_3),
													ex_tFileInputDelimited_3));
										}
									} else {

										row7.code = null;

									}

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

									row7.name = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.name = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 3;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.name_short = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.name_short = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 4;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.name_tv = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.name_tv = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 5;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.gender = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.gender = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 6;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.function = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.function = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 7;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.country_code = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.country_code = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 8;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.country = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.country = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 9;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.country_long = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.country_long = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 10;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.nationality = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.nationality = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 11;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.nationality_long = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.nationality_long = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 12;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.nationality_code = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.nationality_code = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 13;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									if (rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3].length() > 0) {
										try {

											row7.height = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3]);

										} catch (java.lang.Exception ex_tFileInputDelimited_3) {
											globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
													ex_tFileInputDelimited_3.getMessage());
											rowstate_tFileInputDelimited_3.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"height", "row7",
															rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
															ex_tFileInputDelimited_3),
													ex_tFileInputDelimited_3));
										}
									} else {

										row7.height = null;

									}

								} else {

									row7.height = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 14;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									if (rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3].length() > 0) {
										try {

											row7.weight = ParserUtils.parseTo_Float(
													rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3]);

										} catch (java.lang.Exception ex_tFileInputDelimited_3) {
											globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
													ex_tFileInputDelimited_3.getMessage());
											rowstate_tFileInputDelimited_3.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"weight", "row7",
															rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
															ex_tFileInputDelimited_3),
													ex_tFileInputDelimited_3));
										}
									} else {

										row7.weight = null;

									}

								} else {

									row7.weight = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 15;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.disciplines = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.disciplines = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 16;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.events = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.events = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 17;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									if (rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3].length() > 0) {
										try {

											row7.birth_date = ParserUtils.parseTo_Date(
													rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
													"yyyy-mm-dd");

										} catch (java.lang.Exception ex_tFileInputDelimited_3) {
											globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
													ex_tFileInputDelimited_3.getMessage());
											rowstate_tFileInputDelimited_3.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"birth_date", "row7",
															rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3],
															ex_tFileInputDelimited_3),
													ex_tFileInputDelimited_3));
										}
									} else {

										row7.birth_date = null;

									}

								} else {

									row7.birth_date = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 18;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.birth_place = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.birth_place = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 19;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.birth_country = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.birth_country = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 20;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.residence_place = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.residence_place = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 21;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.residence_country = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.residence_country = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 22;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.nickname = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.nickname = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 23;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.hobbies = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.hobbies = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 24;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.occupation = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.occupation = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 25;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.education = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.education = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 26;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.family = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.family = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 27;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.lang = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.lang = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 28;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.coach = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.coach = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 29;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.reason = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.reason = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 30;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.hero = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.hero = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 31;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.influence = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.influence = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 32;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.philosophy = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.philosophy = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 33;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.sporting_relatives = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.sporting_relatives = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 34;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.ritual = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.ritual = null;

								}

								columnIndexWithD_tFileInputDelimited_3 = 35;

								if (columnIndexWithD_tFileInputDelimited_3 < rowtFileInputDelimited_3.length) {

									row7.other_sports = rowtFileInputDelimited_3[columnIndexWithD_tFileInputDelimited_3];

								} else {

									row7.other_sports = null;

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

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row7"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;
								Var.height = row7.height == null || row7.height == 0 ? "UNKNOWN"
										: String.valueOf(row7.height);
								Var.weight = row7.weight == null || row7.weight == 0 ? "UNKNOWN"
										: String.valueOf(row7.weight);
								Var.nationality = row7.nationality == "" ? "UNKNOWN" : row7.nationality;
								Var.nationality_long = row7.nationality_long == "" ? "UNKNOWN" : row7.nationality;
								Var.nationality_code = row7.nationality_code == "" ? "UNKNOWN" : row7.nationality;
								Var.birth_place = row7.birth_place == "" ? "UNKNOWN" : row7.birth_place;
								Var.birth_country = row7.birth_country == "" ? "UNKNOWN" : row7.birth_country;
								Var.residence_place = row7.residence_place == "" ? "UNKNOWN" : row7.residence_place;
								Var.residence_country = row7.residence_country == "" ? "UNKNOWN"
										: row7.residence_country;
								Var.nickname = row7.nickname == "" ? "UNKNOWN" : row7.nickname;
								Var.hobbies = row7.hobbies == "" ? "UNKNOWN" : row7.hobbies;
								Var.occupation = row7.occupation == "" ? "UNKNOWN" : row7.occupation;
								Var.education = row7.education == "" ? "UNKNOWN" : row7.education;
								Var.family = row7.family == "" ? "UNKNOWN" : row7.family;
								Var.lang = row7.lang == "" ? "UNKNOWN" : row7.lang;
								Var.coach = row7.coach == "" ? "UNKNOWN" : row7.coach;
								Var.reason = row7.reason == "" ? "UNKNOWN" : row7.reason;
								Var.hero = row7.hero == "" ? "UNKNOWN" : row7.hero;
								Var.influence = row7.influence == "" ? "UNKNOWN" : row7.influence;
								Var.philosophy = row7.philosophy == "" ? "UNKNOWN" : row7.philosophy;
								Var.sporting_relatives = row7.sporting_relatives == "" ? "UNKNOWN"
										: row7.sporting_relatives;
								Var.other_sports = row7.other_sports == "" ? "UNKNOWN" : row7.other_sports;
								Var.ritual = row7.ritual == "" ? "UNKNOWN" : row7.ritual;// ###############################
								// ###############################
								// # Output tables

								out1 = null;

// # Output table : 'out1'
								out1_tmp.code = row7.code;
								out1_tmp.current = row7.current;
								out1_tmp.name = row7.name;
								out1_tmp.name_short = row7.name_short;
								out1_tmp.name_tv = row7.name_tv;
								out1_tmp.gender = row7.gender;
								out1_tmp.function = row7.function;
								out1_tmp.country_code = row7.country_code;
								out1_tmp.country = row7.country;
								out1_tmp.country_long = row7.country_long;
								out1_tmp.nationality = Var.nationality;
								out1_tmp.nationality_long = row7.nationality_long;
								out1_tmp.nationality_code = row7.nationality_code;
								out1_tmp.height = Var.height;
								out1_tmp.weight = Var.weight;
								out1_tmp.disciplines = row7.disciplines;
								out1_tmp.events = row7.events;
								out1_tmp.birth_date = row7.birth_date;
								out1_tmp.birth_place = Var.birth_place;
								out1_tmp.birth_country = Var.birth_country;
								out1_tmp.residence_place = Var.residence_place;
								out1_tmp.residence_country = Var.residence_country;
								out1_tmp.nickname = Var.nickname;
								out1_tmp.hobbies = Var.hobbies;
								out1_tmp.occupation = Var.occupation;
								out1_tmp.education = Var.education;
								out1_tmp.family = Var.family;
								out1_tmp.lang = Var.lang;
								out1_tmp.coach = Var.coach;
								out1_tmp.reason = Var.reason;
								out1_tmp.hero = Var.hero;
								out1_tmp.influence = Var.influence;
								out1_tmp.philosophy = Var.philosophy;
								out1_tmp.sporting_relatives = Var.sporting_relatives;
								out1_tmp.ritual = Var.ritual;
								out1_tmp.other_sports = Var.other_sports;
								out1 = out1_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "out1"
							if (out1 != null) {

								/**
								 * [tFileOutputDelimited_3 main ] start
								 */

								currentComponent = "tFileOutputDelimited_3";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out1"

									);
								}

								String[] rowtFileOutputDelimited_3 = new String[36];
								rowtFileOutputDelimited_3[0] = out1.code == null ? null : String.valueOf(out1.code);
								rowtFileOutputDelimited_3[1] = out1.current == null ? null : out1.current;
								rowtFileOutputDelimited_3[2] = out1.name == null ? null : out1.name;
								rowtFileOutputDelimited_3[3] = out1.name_short == null ? null : out1.name_short;
								rowtFileOutputDelimited_3[4] = out1.name_tv == null ? null : out1.name_tv;
								rowtFileOutputDelimited_3[5] = out1.gender == null ? null : out1.gender;
								rowtFileOutputDelimited_3[6] = out1.function == null ? null : out1.function;
								rowtFileOutputDelimited_3[7] = out1.country_code == null ? null : out1.country_code;
								rowtFileOutputDelimited_3[8] = out1.country == null ? null : out1.country;
								rowtFileOutputDelimited_3[9] = out1.country_long == null ? null : out1.country_long;
								rowtFileOutputDelimited_3[10] = out1.nationality == null ? null : out1.nationality;
								rowtFileOutputDelimited_3[11] = out1.nationality_long == null ? null
										: out1.nationality_long;
								rowtFileOutputDelimited_3[12] = out1.nationality_code == null ? null
										: out1.nationality_code;
								rowtFileOutputDelimited_3[13] = out1.height == null ? null : out1.height;
								rowtFileOutputDelimited_3[14] = out1.weight == null ? null : out1.weight;
								rowtFileOutputDelimited_3[15] = out1.disciplines == null ? null : out1.disciplines;
								rowtFileOutputDelimited_3[16] = out1.events == null ? null : out1.events;
								rowtFileOutputDelimited_3[17] = out1.birth_date == null ? null
										: FormatterUtils.format_Date(out1.birth_date, "yyyy-mm-dd");
								rowtFileOutputDelimited_3[18] = out1.birth_place == null ? null : out1.birth_place;
								rowtFileOutputDelimited_3[19] = out1.birth_country == null ? null : out1.birth_country;
								rowtFileOutputDelimited_3[20] = out1.residence_place == null ? null
										: out1.residence_place;
								rowtFileOutputDelimited_3[21] = out1.residence_country == null ? null
										: out1.residence_country;
								rowtFileOutputDelimited_3[22] = out1.nickname == null ? null : out1.nickname;
								rowtFileOutputDelimited_3[23] = out1.hobbies == null ? null : out1.hobbies;
								rowtFileOutputDelimited_3[24] = out1.occupation == null ? null : out1.occupation;
								rowtFileOutputDelimited_3[25] = out1.education == null ? null : out1.education;
								rowtFileOutputDelimited_3[26] = out1.family == null ? null : out1.family;
								rowtFileOutputDelimited_3[27] = out1.lang == null ? null : out1.lang;
								rowtFileOutputDelimited_3[28] = out1.coach == null ? null : out1.coach;
								rowtFileOutputDelimited_3[29] = out1.reason == null ? null : out1.reason;
								rowtFileOutputDelimited_3[30] = out1.hero == null ? null : out1.hero;
								rowtFileOutputDelimited_3[31] = out1.influence == null ? null : out1.influence;
								rowtFileOutputDelimited_3[32] = out1.philosophy == null ? null : out1.philosophy;
								rowtFileOutputDelimited_3[33] = out1.sporting_relatives == null ? null
										: out1.sporting_relatives;
								rowtFileOutputDelimited_3[34] = out1.ritual == null ? null : out1.ritual;
								rowtFileOutputDelimited_3[35] = out1.other_sports == null ? null : out1.other_sports;
								nb_line_tFileOutputDelimited_3++;
								resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);
								CsvWritertFileOutputDelimited_3.writeNext(rowtFileOutputDelimited_3);

								tos_count_tFileOutputDelimited_3++;

								/**
								 * [tFileOutputDelimited_3 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_3 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_3";

								/**
								 * [tFileOutputDelimited_3 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_3 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_3";

								/**
								 * [tFileOutputDelimited_3 process_data_end ] stop
								 */

							} // End of branch "out1"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
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
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 end ] start
				 */

				currentComponent = "tFileOutputDelimited_3";

				if (CsvWritertFileOutputDelimited_3 != null) {
					CsvWritertFileOutputDelimited_3.close();
				}

				globalMap.put("tFileOutputDelimited_3_NB_LINE", nb_line_tFileOutputDelimited_3);

				resourceMap.put("finish_tFileOutputDelimited_3", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out1");
				}

				ok_Hash.put("tFileOutputDelimited_3", true);
				end_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_3 end ] stop
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
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_3";

				if (resourceMap.get("finish_tFileOutputDelimited_3") == null) {

					com.talend.csv.CSVWriter CsvWritertFileOutputDelimited_3 = (com.talend.csv.CSVWriter) resourceMap
							.get("CsvWriter_tFileOutputDelimited_3");

					if (CsvWritertFileOutputDelimited_3 != null) {
						CsvWritertFileOutputDelimited_3.close();
					}

				}

				/**
				 * [tFileOutputDelimited_3 finally ] stop
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
		final completude_athletes completude_athletesClass = new completude_athletes();

		int exitCode = completude_athletesClass.runJobInTOS(args);

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
			java.io.InputStream inContext = completude_athletes.class.getClassLoader().getResourceAsStream(
					"projettalend1/completude_athletes_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = completude_athletes.class.getClassLoader()
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
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : completude_athletes");
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
 * 392111 characters generated by Talend Open Studio for Data Integration on the
 * 9 janvier 2025 à 11:26:31 CET
 ************************************************************************************************/