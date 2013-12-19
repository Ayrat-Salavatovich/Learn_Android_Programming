package ayrat.salavatovich.gmail.com.day_59.simplelistevents;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView listViewMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		dbHelper = new DBHelper(this, null);
		db = dbHelper.getWritableDatabase();

		if (countRecords() == 0)
			fillEmptyDB();

		listViewMain = (ListView) findViewById(R.id.listViewMain);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_list_item_1, getCities());
		listViewMain.setAdapter(adapter);

		listViewMain.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showMessage(getString(R.string.item_click, position, id));
			}

		});

		listViewMain.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				showMessage(getString(R.string.item_selected, position, id));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				showMessage(R.string.nothing_selected);
			}
		});

		listViewMain.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				showMessage(getString(R.string.scroll_state_changed,
						scrollState));
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				showMessage(getString(R.string.scroll, firstVisibleItem,
						visibleItemCount, totalItemCount));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private int countRecords() {
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
				null, null);
		int countRecords = cursor.getCount();
		cursor.close();

		return countRecords;
	}

	private void fillEmptyDB() {
		ContentValues values = new ContentValues();
		db.beginTransaction();
		try {
			for (int i = 0; i < cities.length; i++) {
				values.put(DBHelper.COLUMN_NAME, cities[i]);
				db.insert(DBHelper.TABLE_NAME, null, values);
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	private String[] getCities() {
		String[] cities = new String[countRecords()];
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
				null, null);

		if (cursor.moveToFirst()) {
			int i = 0;
			int nameColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_NAME);

			do {
				cities[i++] = cursor.getString(nameColumnIndex);
			} while (cursor.moveToNext());
		}

		return cities;
	}

	private void showMessage(int resId) {
		Toast.makeText(this, resId, Toast.LENGTH_LONG).show();
	}

	private void showMessage(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	private DBHelper dbHelper;
	private SQLiteDatabase db;

	private String[] cities = { "Abrantes", "Agualva-Cacém", "Águeda",
			"Albufeira", "Alcácer do Sal", "Alcobaça", "Almada", "Almeirim",
			"Alverca do Ribatejo", "Amadora", "Amarante", "Amora", "Anadia",
			"Angra do", "Heroísmo", "Aveiro", "Barcelos", "Barreiro", "Beja",
			"Braga", "Bragança", "Caldas da Rainha", "Câmara de Lobos",
			"Caniço", "Cantanhede", "Cartaxo", "Castelo Branco", "Chaves",
			"Coimbra", "Costa da Caparica", "Covilhã", "Elvas",
			"Entroncamento", "Ermesinde", "Esmoriz", "Espinho", "Esposende",
			"Estarreja", "Estremoz", "Évora", "Fafe", "Faro", "Fátima",
			"Felgueiras", "Figueira da Foz", "Fiães", "Freamunde", "Funchal",
			"Fundão", "Gafanha da Nazaré", "Gandra", "Gondomar", "Gouveia",
			"Guarda", "Guimarães", "Horta", "Ílhavo", "Lagoa", "Lagos",
			"Lamego", "Leiria", "Lisbon", "Lixa", "Loulé", "Loures", "Lourosa",
			"Macedo de Cavaleiros", "Maia", "Mangualde", "Marco de Canaveses",
			"Marinha Grande", "Matosinhos", "Mealhada", "Mêda",
			"Miranda do Douro", "Mirandela", "Montemor-o-Novo", "Montijo",
			"Moura", "Odivelas", "Olhão da Restauração", "Oliveira de Azeméis",
			"Oliveira do Bairro", "Oliveira do Hospital", "Ourém", "Ovar",
			"Paços de Ferreira", "Paredes", "Penafiel", "Peniche",
			"Peso da Régua", "Pinhel", "Pombal", "Ponta Delgada",
			"Ponte de Sor", "Portalegre", "Portimão", "Porto",
			"Vila Baleira (a.k.a. Porto Santo)", "Póvoa de Santa Iria",
			"Póvoa de Varzim", "Praia da Vitória", "Quarteira", "Queluz",
			"Rebordosa", "Reguengos de Monsaraz", "Ribeira Grande",
			"Rio Maior", "Rio Tinto", "Sabugal", "Sacavém", "Santa Comba Dão",
			"Santa Cruz", "Santa Maria da Feira", "Santana", "Santarém",
			"Santiago do Cacém", "Santo Tirso", "São João da Madeira",
			"São Mamede de Infesta", "São Salvador de Lordelo", "Seia",
			"Seixal", "Serpa", "Setúbal", "Silves", "Sines", "Tarouca",
			"Tavira", "Tomar", "Tondela", "Torres Novas", "Torres Vedras",
			"Trancoso", "Trofa", "Valbom", "Vale de Cambra", "Valongo",
			"Valpaços", "Vendas Novas", "Viana do Castelo", "Vila do Conde",
			"Vila Franca de Xira", "Vila Nova de Famalicão",
			"Vila Nova de Foz Côa", "Vila Nova de Gaia",
			"Vila Nova de Santo André", "Vila Real",
			"Vila Real de Santo António", "Viseu", "Vizela" };

}
