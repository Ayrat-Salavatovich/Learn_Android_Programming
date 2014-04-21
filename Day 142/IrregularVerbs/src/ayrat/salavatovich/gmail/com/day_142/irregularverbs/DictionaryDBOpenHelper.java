package ayrat.salavatovich.gmail.com.day_142.irregularverbs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DictionaryDBOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "Dictionary.db";

	public class Languages {
		public static final String DATABASE_TABLE = "Languages";
		public static final String KEY_ID = "_pk";
		public static final String KEY_LANGUAGE_COLUMN = "LANGUAGE";

		public static final String CREATE_TABLE = "CREATE TABLE "
				+ DATABASE_TABLE + " { " + KEY_ID
				+ "INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_LANGUAGE_COLUMN
				+ " TEXT NOT NULL" + " };";
	}

	public class EnglishWordsBaseForm {
		public static final String DATABASE_TABLE = "EnglishWordsBaseForm";
		public static final String KEY_ID = "_pk";
		public static final String KEY_ENGLISH_WORD_BASE_FORM_COLUMN = "ENGLISH_WORD_BASE_FORM";
		public static final String KEY_ENGLISH_WORD_BASE_FORM_TRANSCRIPT_FK_COLUMN = "ENGLISH_WORD_BASE_FORM_TRANSCRIPT_FK";
		public static final String KEY_ENGLISH_WORD_BASE_FORM_SOUND_FK_COLUMN = "ENGLISH_WORD_BASE_FORM_SOUND_FK";
		public static final String KEY_ENGLISH_WORD_BASE_FORM_PICTURE_FK_COLUMN = "ENGLISH_WORD_BASE_FORM_PICTURE_FK";
	}

	public class EnglishWordsSimplePast {
		public static final String DATABASE_TABLE = "EnglishWordsSimplePast";
		public static final String KEY_ID = "_pk";
		public static final String KEY_ENGLISH_WORD_SIMPLE_PAST_BASE_FORM_FK_COLUMN = "ENGLISH_WORD_BASE_FORM_FK";
		public static final String KEY_ENGLISH_WORD_SIMPLE_PAST_TRANSCRIPT_FK_COLUMN = "ENGLISH_WORD_SIMPLE_PAST_TRANSCRIPT_FK";
		public static final String KEY_ENGLISH_WORD_SIMPLE_PAST_COLUMN = "ENGLISH_WORD_SIMPLE_PAST";
		public static final String KEY_ENGLISH_WORD_SIMPLE_PAST_SOUND_FK_COLUMN = "ENGLISH_WORD_SIMPLE_PAST_SOUND_FK";
		public static final String KEY_ENGLISH_WORD_SIMPLE_PAST_PICTURE_FK_COLUMN = "ENGLISH_WORD_SIMPLE_PAST_PICTURE_FK";
	}

	public class EnglishWordsPastParticiple {
		public static final String DATABASE_TABLE = "EnglishWordsPastParticiple";
		public static final String KEY_ID = "_pk";
		public static final String KEY_ENGLISH_WORD_PAST_PARTICIPLE_BASE_FORM_FK_COLUMN = "ENGLISH_WORD_BASE_FORM_FK";
		public static final String KEY_ENGLISH_WORD_PAST_PARTICIPLE_TRANSCRIPT_FK_COLUMN = "ENGLISH_WORD_PAST_PARTICIPLE_TRANSCRIPT_FK";
		public static final String KEY_ENGLISH_WORD_PAST_PARTICIPLE_COLUMN = "ENGLISH_WORD_PAST_PARTICIPLE";
		public static final String KEY_ENGLISH_WORD_PAST_PARTICIPLE_SOUND_FK_COLUMN = "ENGLISH_WORD_PAST_PARTICIPLE_SOUND_FK";
		public static final String KEY_ENGLISH_WORD_PAST_PARTICIPLE_PICTURE_FK_COLUMN = "ENGLISH_WORD_PAST_PARTICIPLE_PICTURE_FK";
	}

	public class RussianWords {
		public static final String DATABASE_TABLE = "RussianWords";
		public static final String KEY_ID = "_pk";
		public static final String KEY_RUSSIAN_WORD_COLUMN = "RUSSIAN_WORD";
		public static final String KEY_RUSSIAN_WORD_SOUND_FK_COLUMN = "RUSSIAN_WORD_SOUND_FK";
		public static final String KEY_RUSSIAN_WORD_PICTURE_FK_COLUMN = "RUSSIAN_WORD_PICTURE_FK";
	}

	public class Sounds {
		public static final String DATABASE_TABLE = "Sounds";
		public static final String KEY_ID = "_pk";
		public static final String KEY_TABLE_SOUND_URI_COLUMN = "SOUND_URI";
	}

	public class Pictures {
		public static final String DATABASE_TABLE = "Pictures";
		public static final String KEY_ID = "_pk";
		public static final String KEY_TABLE_PICTURE_URI_COLUMN = "PICTURE_URI";
	}

	public class Transcripts {
		public static final String DATABASE_TABLE = "Transcripts";
		public static final String KEY_ID = "_pk";
		public static final String KEY_TRANSCRIPT_COLUMN = "TRANSCRIPT";
	}

	public class VirbForms {
		public static final String DATABASE_TABLE = "VirbForms";
		public static final String KEY_ID = "_pk";
		public static final String KEY_VIRB_FORM_COLUMN = "VIRB_FORM";
	}

	public class EnglishRussianDictionary {
		public static final String DATABASE_TABLE = "EnglishRussianDictionary";
		public static final String KEY_ID = "_pk";
		public static final String KEY_ENGLISH_RUSSIAN_DICTIONARY_ENGLISH_WORD_FK_COLUMN = "ENGLISH_RUSSIAN_DICTIONARY_ENGLISH_WORD_FK";
		public static final String KEY_ENGLISH_RUSSIAN_DICTIONARY_ENGLISH_WORD_VIRB_FORM_FK_COLUMN = "ENGLISH_RUSSIAN_DICTIONARY_ENGLISH_WORD_VIRB_FORM_FK";
		public static final String KEY_ENGLISH_RUSSIAN_DICTIONARY_RUSSIAN_WORD_FK_COLUMN = "ENGLISH_RUSSIAN_DICTIONARY_RUSSIAN_WORD_FK";

	}

	public class RussianEnglishDictionary {
		public static final String DATABASE_TABLE = "RussianEnglishDictionary";
		public static final String KEY_ID = "_pk";
		public static final String KEY_RUSSIAN_ENGLISH_DICTIONARY_RUSSIAN_WORD_FK_COLUMN = "RUSSIAN_ENGLISH_DICTIONARY_RUSSIAN_WORD_FK";
		public static final String KEY_RUSSIAN_ENGLISH_DICTIONARY_ENGLISH_WORD_FK_COLUMN = "RUSSIAN_ENGLISH_DICTIONARY_ENGLISH_WORD_FK";
		public static final String KEY_RUSSIAN_ENGLISH_DICTIONARY_ENGLISH_WORD_VIRB_FORM_FK_COLUMN = "RUSSIAN_ENGLISH_DICTIONARY_ENGLISH_WORD_VIRB_FORM_FK";

	}

	public DictionaryDBOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
