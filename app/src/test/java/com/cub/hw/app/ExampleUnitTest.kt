package com.cub.hw.app

import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap
import kotlin.math.log
import kotlin.math.max

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//        stringReverse()
//        stockPick()
//        stringReduction("abcabc")
        logDumpGetUnique()
    }

    fun stringReverse() {
        val aa = "1 2 3"
        println("input String : $aa")
        val result = aa.split(" ").reversed()
        var resultString = ""
        for (i in result.indices) {
            if (i == 0) {
                resultString = result[i]
            } else {
                resultString = resultString + " ${result[i]}"
            }
        }
        println("output string : $resultString")
    }

    fun stockPick() {
//        val aaa = arrayOf(44,30,24,32,35,38,15)
        val aaa = arrayOf(44, 40, 39, 38, 36, 15)
        var maxProfit = -1
        for (i in aaa.indices) {
            val buyPrice = aaa[i]
            for (j in i + 1 until aaa.size) {
                val sellPrice = aaa[j]
                val currentProfit = sellPrice - buyPrice
                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit
                }
            }
        }
        println("max profit : $maxProfit")
    }

    fun stringReduction(input: String): Int {
        var tempInput = input
        val itemReductionMap = HashMap<String, String>()
        itemReductionMap["ac"] = "b"
        itemReductionMap["ca"] = "b"
        itemReductionMap["bc"] = "a"
        itemReductionMap["cb"] = "a"
        itemReductionMap["ab"] = "c"
        itemReductionMap["ba"] = "c"

        var isInputLengthChang = false
        while (!isInputLengthChang) {
            val beforeInputCheckLength = tempInput.length
            for (key in itemReductionMap.keys) {
                val replaceValue = itemReductionMap[key] ?: ""
                if (tempInput.contains(key)) {
                    tempInput = tempInput.replaceFirst(key, replaceValue)
                    println("tempInput : $tempInput")
                }
            }
            val afterInputCheckLength = tempInput.length
            isInputLengthChang = beforeInputCheckLength == afterInputCheckLength

        }
        println("result : $tempInput")
        return tempInput.length
    }

    fun logDumpGetUnique(): String {
        var log_dump: String =
            "name=John Trust, username=john3, email=john3@gmail.com, id=434453; " +
                    "name=Hannah Smith, username=hsmith, email=hsm@test.com, id=23312; " +
                    "name=Hannah Smith, username=hsmith, id=3223423, email=hsm@test.com; " +
                    "name=Robert M, username=rm44, id=222342, email=rm@me.com; " +
                    "name=Robert M, username=rm4411, id=5535, email=rm@me.com; " +
                    "name=Susan Vee, username=sv55, id=443432, email=susanv123@me.com; " +
                    "name=Robert Nick, username=rnick33, id=23432, email=rnick@gmail.com; " +
                    "name=Robert Nick II, username=rnickTemp34, id=23432, email=rnick@gmail.com; " +
                    "name=Susan Vee, username=sv55, id=443432, email=susanv123@me.com;"
        //remove id
        val logSplitArray = log_dump.split(";").toMutableList()
        logSplitArray.removeAt(logSplitArray.size-1)
        for(i in logSplitArray.indices){
            val log = logSplitArray[i]
            val logDetailArray = log.split(",");
            var resultLog = ""
            for(j in logDetailArray.indices){
                val detailItemLog = logDetailArray[j]
                if(!detailItemLog.contains("id")){
                    resultLog += detailItemLog
                }
            }
            logSplitArray[i] = resultLog
        }
        //remove duplicates on username
        val checkUsernameMap = LinkedHashMap<String,String>()
        for(i in logSplitArray.indices){
            val logString = logSplitArray[i]
            val usernameStartIndex = logString.indexOf("username")
            val usernameValueEndStart = logString.indexOf("=",usernameStartIndex)
            val usernameValueEndIndex = logString.indexOf(" ",usernameStartIndex)
            val usernameValue = logString.substring(usernameValueEndStart+1,usernameValueEndIndex)
            if (i == 0){
                checkUsernameMap[usernameValue] = logString
            }else{
                if(!checkUsernameMap.contains(usernameValue)){
                    checkUsernameMap[usernameValue] = logString
                }
            }
        }
        var resultString = ""
        for(key in checkUsernameMap.keys) {
            val itemString : String = checkUsernameMap[key] ?:""
            resultString += itemString
        }
        println(resultString)
        return resultString
    }
}