package com.quiz.menu;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * The Class MenuLoader.
 * 
 * @author srikanth.kumar
 */
public class MenuLoader implements ContentHandler {

    /** The menu bar. */
    private JMenuBar menuBar = null;

    /** The menu. */
    private JMenu menu;

    /** The menu item. */
    private JMenuItem menuItem;

    /** The frame. */
    private JFrame frame;

    /** The action listener. */
    private ActionListener actionListener;

    /** The add seperator. */
    private boolean addSeperator;

    /**
     * Instantiates a new menu loader.
     * 
     * @param menuFileName the menu file name
     * @param frame the frame
     */
    public MenuLoader(String menuFileName, JFrame frame) {

        try {
            this.frame = frame;
            if (frame instanceof ActionListener) {
                this.actionListener = (ActionListener) frame;
            }
            if (menuFileName != null) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(menuFileName);
                if (inputStream != null) {
                    readMenuDocument(inputStream);
                }
            }
            
        } catch (SAXException saxException) {
            System.err.println(saxException);
        } catch (IOException ioException) {
            System.err.println(ioException);
        }
    }

    /**
     * Instantiates a new menu loader.
     * 
     * @param menuFileName the menu file name
     * @param frame the frame
     * @param actionListener the action listener
     */
    public MenuLoader(String menuFileName, JFrame frame, ActionListener actionListener) {

        try {
            this.frame = frame;
            this.actionListener = actionListener;
            if (menuFileName != null) {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(menuFileName);
                if (inputStream != null) {
                    readMenuDocument(inputStream);
                }
            }
            
        } catch (SAXException saxException) {
            System.err.println(saxException);
        } catch (IOException ioException) {
            System.err.println(ioException);
        }
    }

    /**
     * Read menu document.
     * 
     * @param inputStream the input stream
     * @throws SAXException the sAX exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void readMenuDocument(InputStream inputStream) throws SAXException, IOException {
        // Create SAX 2 parser...
        XMLReader xMLReader = XMLReaderFactory.createXMLReader();
        // Set the ContentHandler...
        // XMLParser xMLParser = new XMLParser();
        xMLReader.setContentHandler(this);
        // Parse the file...
        xMLReader.parse(new InputSource(inputStream));
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    public void characters(char[] character, int start, int length)
            throws SAXException {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endDocument()
     */
    public void endDocument() throws SAXException {
        if (frame != null && menuBar != null) {
            frame.setJMenuBar(menuBar);
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {
        if (localName.equalsIgnoreCase(MenuConstants.MENU)) {
            if (menu != null) {
                menuBar.add(menu);
            }
        } else if (localName.equalsIgnoreCase(MenuConstants.MENU_ITEM)) {
            if (addSeperator) {
                menu.addSeparator();
                addSeperator = false;
            } else {
                if (menuItem != null) {
                    menu.add(menuItem);
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
     */
    public void endPrefixMapping(String arg0) throws SAXException {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
     */
    public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
            throws SAXException {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
     */
    public void processingInstruction(String arg0, String arg1)
            throws SAXException {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
     */
    public void setDocumentLocator(Locator arg0) {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
     */
    public void skippedEntity(String arg0) throws SAXException {
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    public void startDocument() throws SAXException {
        menuBar = new JMenuBar();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String namespaceURI, String localName,
            String qName, Attributes attributes) throws SAXException {
        if (localName.equalsIgnoreCase(MenuConstants.MENU)) {
            menu = new JMenu();
            if (attributes != null) {
                String value = attributes.getValue(MenuConstants.NAME);
                if (value != null) {
                    menu.setText(value);
                }
                value = attributes.getValue(MenuConstants.ACCESSKEY);
                if (value != null) {
                    menu.setMnemonic(value.charAt(0));
                }
            }
        } else if (localName.equalsIgnoreCase(MenuConstants.MENU_ITEM)) {
            if (attributes != null) {
                String value = attributes.getValue(MenuConstants.NAME);
                if (value != null) {
                    if (value.equalsIgnoreCase(MenuConstants.SEPERATOR)) {
                        addSeperator = true;
                    } else {
                        menuItem = new JMenuItem();
                        menuItem.setText(value);
                        menuItem.addActionListener(actionListener);
                        value = attributes.getValue(MenuConstants.ACCESSKEY);
                        if (value != null) {
                            menuItem.setMnemonic(value.charAt(0));
                        }
                        value = attributes.getValue(MenuConstants.ACTION_COMMAND);
                        if (value != null) {
                            menuItem.setActionCommand(value);
                        }
                    }
                }
            }
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
     */
    public void startPrefixMapping(String arg0, String arg1)
            throws SAXException {
    }

}
