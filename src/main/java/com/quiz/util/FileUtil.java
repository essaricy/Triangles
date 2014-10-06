package com.quiz.util;

import java.awt.Component;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 * The Class FileUtil.
 * 
 * @author srikanth.kumar
 */
public class FileUtil {

	/** The Constant SHORT_EXT_PDF. */
	public static final int SHORT_EXT_PDF = 1;
	
	/** The Constant SHORT_EXT_XLSX. */
	public static final int SHORT_EXT_XLSX = 2;
	
	/** The Constant SHORT_EXT_XLS. */
	public static final int SHORT_EXT_XLS = 3;
	
	/** The Constant SHORT_EXT_JPG. */
	public static final int SHORT_EXT_JPG = 4;
	
	/** The Constant SHORT_EXT_PNG. */
	public static final int SHORT_EXT_PNG = 5;
	
	/** The Constant SHORT_EXT_DOC. */
	public static final int SHORT_EXT_DOC = 6;
	
	/** The Constant SHORT_EXT_TXT. */
	public static final int SHORT_EXT_TXT = 7;
	
	/** The Constant SHORT_EXT_MSG. */
	public static final int SHORT_EXT_MSG = 8;
	
	/** The Constant SHORT_EXT_CSV. */
	public static final int SHORT_EXT_CSV = 9;
	
	/** The Constant SHORT_EXT_GIF. */
	public static final int SHORT_EXT_GIF = 10;

	/** The Constant EXT_PDF. */
	public static final String EXT_PDF = "PDF";
	
	/** The Constant EXT_XLSX. */
	public static final String EXT_XLSX = "XLSX";
	
	/** The Constant EXT_XLS. */
	public static final String EXT_XLS = "XLS";
	
	/** The Constant EXT_JPG. */
	public static final String EXT_JPG = "JPG";
	
	/** The Constant EXT_PNG. */
	public static final String EXT_PNG = "PNG";
	
	/** The Constant EXT_DOC. */
	public static final String EXT_DOC = "DOC";
	
	/** The Constant EXT_TXT. */
	public static final String EXT_TXT = "TXT";
	
	/** The Constant EXT_MSG. */
	public static final String EXT_MSG = "MSG";
	
	/** The Constant EXT_CSV. */
	public static final String EXT_CSV = "CSV";
	
	/** The Constant EXT_GIF. */
	public static final String EXT_GIF = "GIF";
    
    /** The Constant EXT_ZIP. */
    public static final String EXT_ZIP = "ZIP";

	/**
     * Gets the files.
     * 
     * @param source the source
     * @return the files
     */
	public static List getFiles(File source) {
		File file = null;
		List filesList = new ArrayList();
		if (source != null) {
			File[] listFiles = source.listFiles();
			if (listFiles != null) {
				for (int index = 0; index < listFiles.length; index++) {
					file = listFiles[index];
					if (file != null && file.isFile()) {
						filesList.add(file);
					} else {
						filesList.addAll(getFiles(file));
					}
				}
			}
		}
		return filesList;
	}

    /**
     * Gets the files.
     * 
     * @param source the source
     * @param fileFilter the file filter
     * @return the files
     */
    public static List getFiles(File source, FileFilter fileFilter) {
        File file = null;
        List filesList = new ArrayList();
        if (source != null) {
            File[] listFiles = source.listFiles(fileFilter);
            if (listFiles != null) {
                for (int index = 0; index < listFiles.length; index++) {
                    file = listFiles[index];
                    if (file != null && file.isFile()) {
                        filesList.add(file);
                    } else {
                        filesList.addAll(getFiles(file, fileFilter));
                    }
                }
            }
        }
        return filesList;
    }

    /**
     * Gets the files.
     * 
     * @param source the source
     * @param filenameFilter the filename filter
     * @return the files
     */
    public static List getFiles(File source, FilenameFilter filenameFilter) {
        File file = null;
        List filesList = new ArrayList();
        if (source != null) {
            File[] listFiles = source.listFiles(filenameFilter);
            if (listFiles != null) {
                for (int index = 0; index < listFiles.length; index++) {
                    file = listFiles[index];
                    if (file != null && file.isFile()) {
                        filesList.add(file);
                    } else {
                        filesList.addAll(getFiles(file, filenameFilter));
                    }
                }
            }
        }
        return filesList;
    }

    /**
     * Gets the extension.
     * 
     * @param fileName the file name
     * @return the extension
     */
    public static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
	}

    /**
     * Gets the extension.
     * 
     * @param file the file
     * @return the extension
     */
    public static String getExtension(File file) {
        String fileName = file.getAbsolutePath();
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
    }

	/**
     * Gets the short extension.
     * 
     * @param fileName the file name
     * @return the short extension
     */
	public static int getShortExtension(String fileName) {
		int returnValue = 0;
		String extension = getExtension(fileName);
		if (extension.equalsIgnoreCase(EXT_PDF)) {
			returnValue = SHORT_EXT_PDF;
		} else if (extension.equalsIgnoreCase(EXT_XLSX)) {
			returnValue = SHORT_EXT_XLSX;
		} else if (extension.equalsIgnoreCase(EXT_XLS)) {
			returnValue = SHORT_EXT_XLS;
		} else if (extension.equalsIgnoreCase(EXT_TXT)) {
			returnValue = SHORT_EXT_TXT;
		} else if (extension.equalsIgnoreCase(EXT_JPG)) {
			returnValue = SHORT_EXT_JPG;
		} else if (extension.equalsIgnoreCase(EXT_PNG)) {
			returnValue = SHORT_EXT_PNG;
		} else if (extension.equalsIgnoreCase(EXT_DOC)) {
			returnValue = SHORT_EXT_DOC;
		} else if (extension.equalsIgnoreCase(EXT_MSG)) {
			returnValue = SHORT_EXT_MSG;
		} else if (extension.equalsIgnoreCase(EXT_CSV)) {
			returnValue = SHORT_EXT_CSV;
		} else if (extension.equalsIgnoreCase(EXT_GIF)) {
			returnValue = SHORT_EXT_GIF;
		}
		return returnValue;
		
	}


    /**
     * Gets the unique mail id.
     * 
     * @param fileName the file name
     * @return the unique mail id
     */
    public static String getUniqueMailId(String fileName) {
        String returnValue = null;
        if (fileName != null) {
            if (fileName.lastIndexOf("_") == -1) {
                returnValue = fileName.substring(0, fileName.lastIndexOf("."));
            } else {
                returnValue = fileName.substring(0, fileName.lastIndexOf("_"));
            }
        } 
        return returnValue;
    }
    
    /**
     * Gets the file name.
     * 
     * @param fileName the file name
     * @return the file name
     */
    public static String getFileName(String fileName) {
        String returnValue = null;
        if (fileName != null) {
            returnValue = fileName.substring(0, fileName.lastIndexOf("."));
        } 
        return returnValue;
    }

    /**
     * Gets the file name.
     * 
     * @param file the file
     * @return the file name
     */
    public static String getFileName(File file) {
        String returnValue = null;
        if (file != null) {
            String fileName = file.getName();
            if (fileName != null) {
                returnValue = fileName.substring(0, fileName.lastIndexOf("."));
            } 
            
        }
        return returnValue;
    }

    /**
     * Gets the file types list.
     * 
     * @param fileTypesToSearch the file types to search
     * @return the file types list
     */
    public static List getFileTypesList(String fileTypesToSearch) {
        List fileTypesList = null;
        String fileTypeToSearch = null;
        String trimmedString = null;

        if (fileTypesToSearch != null && fileTypesToSearch.length() > 0) {
            fileTypesList = new ArrayList();
            String[] split = fileTypesToSearch.split(",");
            if (split != null) {
                for (int index = 0; index < split.length; index++) {
                    fileTypeToSearch = split[index];
                    if (fileTypeToSearch != null) {
                        trimmedString = fileTypeToSearch.trim().toUpperCase();
                        if (trimmedString.length() > 0
                                && !fileTypesList.contains(trimmedString)) {
                            fileTypesList.add(trimmedString);
                        }
                    }
                }
            }
        }
        return fileTypesList;
    }

    /**
     * Creates the file.
     * 
     * @param fileName the file name
     * @return the file
     */
    public static File createFile(String fileName) {
        if (fileName != null && fileName.trim().length() > 0) {
            return new File(fileName);
        }
        return null;
    }

    /**
     * Browse file.
     * 
     * @param panel the panel
     * @param selectionMode the selection mode
     * @param defaultFile the default file
     * @param save the save
     * @return the string
     */
    private static String browseFile(Component component,
            int selectionMode, File defaultFile, boolean save) {

        int returnVal = 0;
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(selectionMode);

        fileChooser.setSelectedFile(defaultFile);

        if (save) {
            returnVal = fileChooser.showSaveDialog(component);
        } else {
            returnVal = fileChooser.showOpenDialog(component);
        }
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file.getAbsolutePath();
        }
        return null;
    }

    /**
     * Browse save file.
     * 
     * @param panel the panel
     * @param selectionMode the selection mode
     * @param defaultFile the default file
     * @return the string
     */
    public static String browseSaveFile(JPanel panel,
            int selectionMode, File defaultFile) {
        return browseFile(panel, selectionMode, defaultFile, true);
    }

    /**
     * Browse save file.
     * 
     * @param panel the panel
     * @param defaultFile the default file
     * @return the string
     */
    public static String browseSaveFile(JPanel panel, File defaultFile) {
        return browseFile(panel, JFileChooser.FILES_ONLY, defaultFile, true);
    }

    /**
     * Browse save file.
     * 
     * @param panel the panel
     * @param selectionMode the selection mode
     * @return the string
     */
    public static String browseSaveFile(JPanel panel, int selectionMode) {
        return browseFile(panel, selectionMode, new File(""), true);
    }

    /**
     * Browse open file.
     * 
     * @param panel the panel
     * @param selectionMode the selection mode
     * @param defaultFile the default file
     * @return the string
     */
    public static String browseOpenFile(JPanel panel,
            int selectionMode, File defaultFile) {
        return browseFile(panel, selectionMode, defaultFile, false);
    }

    /**
     * Browse open file.
     * 
     * @param panel the panel
     * @param selectionMode the selection mode
     * @return the string
     */
    public static String browseOpenFile(JPanel panel, int selectionMode) {
        return browseFile(panel, selectionMode, new File(""), false);
    }

    /**
     * Gets the files.
     * 
     * @param source the source
     * @param fileTypesToSearch the file types to search
     * @return the files
     */
    public static List getFiles(File source, List fileTypesToSearch) {
        List returnList = null;
        if (fileTypesToSearch == null) {
            returnList = getFiles(source);
        } else {
            returnList = getFiles(source, creatFileFilter(fileTypesToSearch));
        }
        return returnList;
    }

    /**
     * Creat file filter.
     * 
     * @param fileTypesToSearch the file types to search
     * @return the file filter
     */
    public static FileFilter creatFileFilter(final List fileTypesToSearch) {
        FileFilter fileFilter = null;

        if (fileTypesToSearch != null) {
            fileFilter = new FileFilter() {
                public boolean accept(File file) {
                    String extension = FileUtil.getExtension(file.getAbsolutePath());
                    if (fileTypesToSearch.contains(extension.toUpperCase())) {
                        return true;
                    }
                    return false;
                }
            };
        }
        return fileFilter;
    }

    /**
     * Gets the random file.
     * 
     * @param currentFile the current file
     * @param randomId the random id
     * @return the random file
     */
    public static String getRandomFile(File currentFile, String randomId) {
        return randomId + "." + getExtension(currentFile);
    }

    /**
     * Gets the random file.
     * 
     * @param currentFile the current file
     * @param randomId the random id
     * @param fileIndex the file index
     * @return the random file
     */
    public static String getRandomFile(File currentFile, String randomId,
            int fileIndex) {
        return randomId + "_" + fileIndex + "." + getExtension(currentFile).toLowerCase();
    }

    /**
     * Copy files.
     * 
     * @param inputDirectory the input directory
     * @param outputDirectory the output directory
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void copyFiles(File inputDirectory, File outputDirectory) throws IOException {
        if (inputDirectory != null && inputDirectory.exists()) {
            if (!outputDirectory.exists()) {
                outputDirectory.createNewFile();
            }
            File[] files = inputDirectory.listFiles();
            if (files != null) {
                for (int index = 0; index < files.length; index++) {
                    //files
                }
            }
        }
        
    }

    /**
     * Copy folder.
     * 
     * @param source the source
     * @param destination the destination
     * @param assignUniqueNames the assign unique names
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static List copyFolder(File source, File destination,
            boolean assignUniqueNames) throws IOException {

        byte[] buffer = null;
        int length;

        List oldNewFilesMapping = new ArrayList();
        String fileName = null;
        File sourceFile = null;
        File destinationFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            if (source.isDirectory()) {
                // if directory not exists, create it
                if (!destination.exists()) {
                    destination.mkdir();
                }
                // list all the directory contents
                String files[] = source.list();
                for (int index = 0; index < files.length; index++) {
                    fileName = files[index];
                    // construct the src and dest file structure
                    sourceFile = new File(source, fileName);
                    destinationFile = new File(destination, fileName);
                    // recursive copy
                    oldNewFilesMapping.addAll(
                            copyFolder(sourceFile, destinationFile, assignUniqueNames));
                }
            } else {
                // if file, then copy it
                // Use bytes stream to support all file types
                inputStream = new FileInputStream(source);
                if (assignUniqueNames) {
                    destination = new File(destination.getParentFile(), getRandomId() + "." + getExtension(destination).toLowerCase());
                }
                outputStream = new FileOutputStream(destination);

                buffer = new byte[1024];
                // copy the file content in bytes
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                oldNewFilesMapping.add(new File[] {source, destination});
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return oldNewFilesMapping;
    }

    /**
     * Gets the random id.
     * 
     * @return the random id
     */
    public static String getRandomId() {
        SecureRandom random = new SecureRandom(); 
        return new BigInteger(130, random).toString(32).toUpperCase();
    }

    /**
     * Form attachment file name.
     * 
     * @param file the file
     * @param attachmentName the attachment name
     * @param counter the counter
     * @return the string
     */
    public static String formAttachmentFileName(File file, String attachmentName,
            int counter) {
        return getFileName(file) + "_" + counter + "." + getExtension(attachmentName).toLowerCase();
    }

	/**
	 * Browse open file.
	 *
	 * @param panel the panel
	 * @param selectionMode the selection mode
	 * @param defaultFile the default file
	 * @return the string
	 */
	public static String browseOpenFile(Component component, int selectionMode, String defaultFile) {
		return browseFile(component, selectionMode, new File(defaultFile), false);
	}
}
