package ayrat.salavatovich.gmail.com.day_163.creatingxml;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (savedInstanceState == null)
			getFragmentManager().beginTransaction()
					.add(R.id.frameLayout, new PlaceholderFragment()).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.main_fragment, container,
					false);

			TextView textView = (TextView) rootView.findViewById(R.id.textView);

			try {
				DocumentBuilderFactory documentBuilderFactory = createDocumentBuilderFactory();

				DocumentBuilder documentBuilder = createDocumentBuilder(documentBuilderFactory);

				Document document = createDocument(documentBuilder);

				Element rootElement = createElement(document, "catalog");

				setAttributeFor(rootElement, "journal", "TIPOGRAFIA PORTUGUESA");
				setAttributeFor(rootElement, "publisher", "Lisboa: Biblioteca Nacional");

				addElementToEndListOfDocumentNode(document, rootElement);

				Element articleElement = createElementAndAddToEndListOfElementNode(
						document, rootElement, "article");
				Element editionElement = createElementAndAddToEndListOfElementNode(
						document, articleElement, "edition");

				createTextNodeAndAddToEndListOfElementNode(document,
						editionElement, "1926");

				Element titleElement = createElementAndAddToEndListOfElementNode(
						document, articleElement, "title");
				createTextNodeAndAddToEndListOfElementNode(document,
						titleElement, "Bibliografia das obras impressas em Portugal no século XVI");

				Element authorElement = createElementAndAddToEndListOfElementNode(
						document, articleElement, "author");
				createTextNodeAndAddToEndListOfElementNode(document,
						authorElement, "ANSELMO, António Joaquim");

				articleElement = createElementAndAddToEndListOfElementNode(
						document, rootElement, "article");
				editionElement = createElementAndAddToEndListOfElementNode(
						document, articleElement, "edition");
				createTextNodeAndAddToEndListOfElementNode(document,
						editionElement, "1990");

				titleElement = createElementAndAddToEndListOfElementNode(
						document, articleElement, "title");
				createTextNodeAndAddToEndListOfElementNode(document,
						titleElement, "Catálogo dos impressos de tipografia portuguesa do século XVI");

				authorElement = createElementAndAddToEndListOfElementNode(
						document, articleElement, "author");
				createTextNodeAndAddToEndListOfElementNode(document,
						authorElement, "SIMÕES, Maria Alzira Proença");
				
				articleElement = createElementAndAddToEndListOfElementNode(
						document, rootElement, "article");
				editionElement = createElementAndAddToEndListOfElementNode(
						document, articleElement, "edition");
				createTextNodeAndAddToEndListOfElementNode(document,
						editionElement, "1924");

				titleElement = createElementAndAddToEndListOfElementNode(
						document, articleElement, "title");
				createTextNodeAndAddToEndListOfElementNode(document,
						titleElement, "O movimento tipográfico em Portugal no século XVI");

				authorElement = createElementAndAddToEndListOfElementNode(
						document, articleElement, "author");
				createTextNodeAndAddToEndListOfElementNode(document,
						authorElement, "VITERBO, Sousa");

				TransformerFactory factory = createTransformerFactory();
				Transformer transformer = createTransformer(factory);

				Properties outFormat = createPropertiesXML();

				transformer.setOutputProperties(outFormat);

				DOMSource domSource = createDOMSource(document
						.getDocumentElement());

				// Для вывода документа создадим ByteArrayOutputStream
				// и объект StreamResult из ByteArrayOutputStream
				OutputStream output = new ByteArrayOutputStream();
				// StreamResult нужен для преобразования структуры DOM
				StreamResult result = new StreamResult(output);

				// Преобразуем объект Document с помощью метода transform()
				// объекта Transformer
				transformer.transform(domSource, result);

				displayXML(textView, output);

			} catch (ParserConfigurationException e) {
			} catch (TransformerConfigurationException e) {
			} catch (TransformerException e) {
			}

			return rootView;
		}

		/**
		 * Создадим экземпляр объекта DocumentBuilderFactory с помощью
		 * статического метода newInstance()
		 * 
		 * @return объект DocumentBuilderFactory
		 */
		DocumentBuilderFactory createDocumentBuilderFactory() {
			return DocumentBuilderFactory.newInstance();
		}

		/**
		 * Создадим объект DocumentBuilder с помощью метода newDocumentBuilder()
		 * класса DocumentBuilderFactory
		 * 
		 * @param documentBuilderFactory
		 * @return объект DocumentBuilder
		 * @throws ParserConfigurationException
		 */
		DocumentBuilder createDocumentBuilder(
				final DocumentBuilderFactory documentBuilderFactory)
				throws ParserConfigurationException {
			return documentBuilderFactory.newDocumentBuilder();
		}

		/**
		 * XML-документ представлен структурой DOM. Создадим объект Document,
		 * используя метод newDocument() класса DocumentBuilder
		 * 
		 * @param documentBuilder
		 * @return объект Document
		 */
		Document createDocument(final DocumentBuilder documentBuilder) {
			return documentBuilder.newDocument();
		}

		/**
		 * Создадим элемент объекта Document с помощью метода createElement()
		 * 
		 * @param document
		 * @param tagName
		 * @return объект Element
		 * @throws DOMException
		 */
		Element createElement(final Document document, String tagName)
				throws DOMException {
			return document.createElement(tagName);
		}

		/**
		 * Установим необходимые атрибуты у элемента
		 * 
		 * @param element
		 * @param key
		 * @param value
		 * @throws DOMException
		 */
		void setAttributeFor(final Element element, String key, String value)
				throws DOMException {
			element.setAttribute(key, value);
		}

		/**
		 * Добавляем элемент в конец списка элементов объекта Document
		 * 
		 * @param documentNode
		 * @param element
		 * @throws DOMException
		 */
		void addElementToEndListOfDocumentNode(final Document documentNode,
				final Element element) throws DOMException {
			documentNode.appendChild(element);
		}

		/**
		 * Добавляем элемент в конец списка элементов объекта Element
		 * 
		 * @param elementNode
		 * @param element
		 * @throws DOMException
		 */
		void addElementToEndListOfElementNode(final Element elementNode,
				final Element element) throws DOMException {
			elementNode.appendChild(element);
		}

		/**
		 * Добавляем текстовый узел в конец списка элементов объекта Element
		 * 
		 * @param elementNode
		 * @param text
		 * @throws DOMException
		 */
		void addTextToEndListOfElementNode(final Element elementNode,
				final Text text) throws DOMException {
			elementNode.appendChild(text);
		}

		/**
		 * Создадим элемент объекта Document с помощью метода createElement() и
		 * добавляем элемент в конец списка элементов объекта Document
		 * 
		 * @param documentNode
		 * @param tagName
		 * @return объект Element
		 * @throws DOMException
		 */
		Element createElementAndAddToEndListOfDocumentNode(
				final Document documentNode, String tagName)
				throws DOMException {
			Element element = createElement(documentNode, tagName);
			addElementToEndListOfDocumentNode(documentNode, element);

			return element;
		}

		/**
		 * Создадим элемент объекта Document с помощью метода createElement() и
		 * добавляем созданный элемент в конец списка элементов объекта Element
		 * 
		 * @param documentNode
		 * @param elementNode
		 * @param tagName
		 * @return объект Element
		 * @throws DOMException
		 */
		Element createElementAndAddToEndListOfElementNode(
				final Document documentNode, final Element elementNode,
				String tagName) throws DOMException {
			Element element = createElement(documentNode, tagName);
			addElementToEndListOfElementNode(elementNode, element);

			return element;
		}

		/**
		 * Создадим текстовый узел с использованием метода createTextNode()
		 * 
		 * @param documentNode
		 * @param text
		 * @return объект Text
		 */
		Text createTextNode(final Document document, String text) {
			return document.createTextNode(text);
		}

		/**
		 * Создадим текстовый узел с использованием метода createTextNode() и
		 * добавим созданный текстовый узел в конец списка элементов объекта
		 * Element
		 * 
		 * @param documentNode
		 * @param elementNode
		 * @param data
		 * @return объект Text
		 * @throws DOMException
		 */
		Text createTextNodeAndAddToEndListOfElementNode(
				final Document documentNode, final Element elementNode,
				String data) throws DOMException {
			Text text = createTextNode(documentNode, data);
			addTextToEndListOfElementNode(elementNode, text);
			return text;
		}

		/**
		 * Создадим объект TransformerFactory с помощью статического метода
		 * newInstance()
		 * 
		 * @return объект TransformerFactory
		 */
		TransformerFactory createTransformerFactory() {
			return TransformerFactory.newInstance();
		}

		/**
		 * Создадим объект Transformer с помощью метода newTransformer() объекта
		 * TransformerFactory
		 * 
		 * @param transformerFactory
		 * @return объект Transformer
		 * @throws TransformerConfigurationException
		 */
		Transformer createTransformer(
				final TransformerFactory transformerFactory)
				throws TransformerConfigurationException {
			return transformerFactory.newTransformer();
		}

		/**
		 * Создадим объект Properties и зададим свойства XML
		 * 
		 * @return объект Properties
		 */
		Properties createPropertiesXML() {
			Properties outFormat = new Properties();
			outFormat.setProperty(OutputKeys.INDENT, "yes"); // отступы
			outFormat.setProperty(OutputKeys.METHOD, "xml"); // формат метода
			outFormat.setProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); // XML-декларация
			outFormat.setProperty(OutputKeys.VERSION, "1.0"); // версия XML
			outFormat.setProperty(OutputKeys.ENCODING, "UTF-8"); // кодировка

			return outFormat;
		}

		/**
		 * Зададим выходные свойства
		 * 
		 * @param transformer
		 * @param properties
		 */
		void setOutputPropertiesXMLForTransformer(
				final Transformer transformer, final Properties properties) {
			transformer.setOutputProperties(properties);
		}

		/**
		 * Создадим объект DOMSource из объекта Element. Нужен для
		 * преобразования структуры DOM
		 * 
		 * @param element
		 * @return объект DOMSource
		 */
		DOMSource createDOMSource(Element element) {
			return new DOMSource(element);
		}

		void displayXML(final TextView textView, final OutputStream output) {
			String xmlString = output.toString();
			textView.setText(xmlString);
		}

	}

}
