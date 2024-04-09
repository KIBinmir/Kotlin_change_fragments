package ru.myitschool.lab23

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
/*import java.math.BigDecimal
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.sqrt*/

class GeneratedNumber: ViewModel() {
    val answer = MutableLiveData<Double>()

    fun saveData(newValue: Double) {
        answer.value = newValue
    }
    /*private val MeanContainer = MutableLiveData<BigDecimal>()
    private val VarianceContainer = MutableLiveData<BigDecimal>()
    private val ResultContainer = MutableLiveData<Double>()

    fun MeanContainer() = MeanContainer
    fun VarianceContainer() = VarianceContainer
    fun ResultContainer() = ResultContainer

    fun normVal() {
        val x: Double = Math.random()
        val u: Double = MeanContainer().value?.toDouble() ?: 0.0
        val d2: Double = VarianceContainer().value?.toDouble() ?: 1.0
        ResultContainer().value = BigDecimal.valueOf(1/(x*sqrt(2*d2*PI))*exp(-(ln(x)-u)*(ln(x)-u)/2/d2)).toDouble()
    }*/



}