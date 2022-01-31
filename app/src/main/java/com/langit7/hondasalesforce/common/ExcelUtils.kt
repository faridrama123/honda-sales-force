package com.langit7.hondasalesforce.common

import NosAudit
import android.content.Context
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.CellStyle
import com.langit7.hondasalesforce.common.ExcelUtils
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import android.os.Environment
import android.util.Log
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.ss.usermodel.Cell
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.lang.StringBuilder
import java.util.ArrayList
import org.apache.poi.ss.usermodel.VerticalAlignment





/**
 * Excel Worksheet Utility Methods
 *
 *
 * Created by: Ranit Raj Ganguly on 16/04/21.
 */
object ExcelUtils {
    const val TAG = "ExcelUtil"
    private var cell: Cell? = null
    private var sheet: Sheet? = null
    private var workbook: Workbook? = null
    private var headerCellStyle: CellStyle? = null
    private var dataCellStyle: CellStyle? = null

    private var importedExcelData: MutableList<NosAudit>? = null

    /**
     * Import data from Excel Workbook
     *
     * @param context - Application Context
     * @param fileName - Name of the excel file
     * @return importedExcelData
     */


    /**
     * Export Data into Excel Workbook
     *
     * @param context  - Pass the application context
     * @param fileName - Pass the desired fileName for the output excel Workbook
     * @param dataList - Contains the actual data to be displayed in excel
     */
    fun exportDataIntoWorkbook(
        context: Context, fileName: String,
        dataList: List<NosAudit>,
        title : String,
        option1 : String,
        option2 : String,
        option3 : String,
        option4 : String,


        ): Boolean {
        val isWorkbookWrittenIntoStorage: Boolean

        // Check if available and not read only
        if (!isExternalStorageAvailable || isExternalStorageReadOnly) {
            Log.e(TAG, "Storage not available or read only")
            return false
        }

        // Creating a New HSSF Workbook (.xls format)
        workbook = HSSFWorkbook()
        setHeaderCellStyle()
        setDataCellStyle()


        // Creating a New Sheet and Setting width for each column
        sheet = (workbook as HSSFWorkbook).createSheet(Constants.EXCEL_SHEET_NAME)
        (sheet as HSSFSheet?)?.setColumnWidth(0, 15 * 400)
        (sheet as HSSFSheet?)?.setColumnWidth(1, 15 * 400)
        (sheet as HSSFSheet?)?.setColumnWidth(2, 15 * 2400)
        (sheet as HSSFSheet?)?.setColumnWidth(3, 15 * 300)
        (sheet as HSSFSheet?)?.setColumnWidth(4, 15 * 300)
        (sheet as HSSFSheet?)?.setColumnWidth(5, 15 * 300)
        (sheet as HSSFSheet?)?.setColumnWidth(6, 15 * 300)



        setHeaderRow(title, option1,  option2 , option3,option4 )
        fillDataIntoExcel(dataList)
        isWorkbookWrittenIntoStorage = storeExcelInStorage(context, fileName)
        return isWorkbookWrittenIntoStorage
    }

    /**
     * Checks if Storage is READ-ONLY
     *
     * @return boolean
     */
    private val isExternalStorageReadOnly: Boolean
        private get() {
            val externalStorageState = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED_READ_ONLY == externalStorageState
        }

    /**
     * Checks if Storage is Available
     *
     * @return boolean
     */
    private val isExternalStorageAvailable: Boolean
        private get() {
            val externalStorageState = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == externalStorageState
        }

    /**
     * Setup header cell style
     */
    private fun setHeaderCellStyle() {
        headerCellStyle = workbook?.createCellStyle()
        headerCellStyle?.setFillForegroundColor(HSSFColor.AQUA.index)
        headerCellStyle?.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND)
        headerCellStyle?.setAlignment(CellStyle.ALIGN_CENTER)
    }

    private fun setDataCellStyle() {
        dataCellStyle = workbook?.createCellStyle()
        dataCellStyle?.setAlignment(CellStyle.ALIGN_CENTER);
        dataCellStyle?.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    }

    /**
     * Setup Header Row
     */
    private fun setHeaderRow(
        title: String,
        option1: String,
        option2: String,
        option3: String,
        option4: String,
    ) {
        val headerRow = sheet?.createRow(0)
        cell = headerRow?.createCell(0)
        cell?.setCellValue(title)
        cell?.setCellStyle(headerCellStyle)

        cell = headerRow?.createCell(1)
        cell?.setCellValue("")
        cell?.setCellStyle(headerCellStyle)

        cell = headerRow?.createCell(2)
        cell?.setCellValue("Indikator")
        cell?.setCellStyle(headerCellStyle)


        cell = headerRow?.createCell(3)
        cell?.setCellValue(option1)
        cell?.setCellStyle(headerCellStyle)

        cell = headerRow?.createCell(4)
        cell?.setCellValue(option2)
        cell?.setCellStyle(headerCellStyle)

        cell = headerRow?.createCell(5)
        cell?.setCellValue(option3)
        cell?.setCellStyle(headerCellStyle)

        cell = headerRow?.createCell(6)
        cell?.setCellValue(option4)
        cell?.setCellStyle(headerCellStyle)
    }

    /**
     * Fills Data into Excel Sheet
     *
     *
     * NOTE: Set row index as i+1 since 0th index belongs to header row
     *
     * @param dataList - List containing data to be filled into excel
     */
    private fun fillDataIntoExcel(dataList: List<NosAudit>) {
        for (i in dataList.indices) {
            // Create a New Row for every new entry in list
            val rowData = sheet!!.createRow(i + 1)

            // Create Cells for each row
            cell = rowData.createCell(0)
            cell?.setCellValue(dataList[i].title)
            cell?.setCellStyle(dataCellStyle)


            cell = rowData.createCell(1)
            if (dataList[i].subTitle != null) {
                val stringBuilder = StringBuilder()
                    stringBuilder.append(dataList[i].subTitle)
                cell?.setCellValue(stringBuilder.toString())
                cell?.setCellStyle(dataCellStyle)

            } else {
                cell?.setCellValue("")
            }

            cell = rowData.createCell(2)
            cell?.setCellValue(dataList[i].data)


            cell = rowData.createCell(3)

            if(dataList[i].answer==1) {
                cell?.setCellValue("v")
                cell?.setCellStyle(dataCellStyle)

            }


            cell = rowData.createCell(4)
            if(dataList[i].answer==2) {
                cell?.setCellValue("v")
                cell?.setCellStyle(dataCellStyle)

            }

            cell = rowData.createCell(5)
            if(dataList[i].answer==3){
                cell?.setCellValue("v")
                cell?.setCellStyle(dataCellStyle)

            }

            cell = rowData.createCell(6)
            if(dataList[i].answer==4)   {
                cell?.setCellValue("v")
                cell?.setCellStyle(dataCellStyle)

            }



        }
    }

    /**
     * Store Excel Workbook in external storage
     *
     * @param context  - application context
     * @param fileName - name of workbook which will be stored in device
     * @return boolean - returns state whether workbook is written into storage or not
     */
    private fun storeExcelInStorage(context: Context, fileName: String): Boolean {
        var isSuccess: Boolean
        val file = File(context.getExternalFilesDir(null), fileName)
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            workbook!!.write(fileOutputStream)
            Log.e(TAG, "Writing file$file")
            isSuccess = true
        } catch (e: IOException) {
            Log.e(TAG, "Error writing Exception: ", e)
            isSuccess = false
        } catch (e: Exception) {
            Log.e(TAG, "Failed to save file due to Exception: ", e)
            isSuccess = false
        } finally {
            try {
                fileOutputStream?.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        return isSuccess
    }

    /**
     * Retrieve excel from External Storage
     *
     * @param context  - application context
     * @param fileName - name of workbook to be read
     * @return importedExcelData
     */

}