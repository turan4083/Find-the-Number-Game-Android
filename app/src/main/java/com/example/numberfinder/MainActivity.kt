package com.example.numberfinder

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var txtHidden: TextView
    private lateinit var txtHello: TextView
    private var targetNum = -1
    private lateinit var targetNumS: String
    private var guessNumbers = mutableListOf<Int>()
    private lateinit var txtOp: TextView
    private var opString = ""
    private var operandNum = 1
    private var operator = -1
    private var operand1 = 0
    private var operand2 = 0
    private var operatorTimeFlag = false
    private var numTimeFlag = true
    private var opFinishFlag = false
    private var operandIndex = -1
    private var turnLeft = -1
    private var point = -1
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtHidden = findViewById(R.id.hiddentxt)
        txtHello = findViewById(R.id.txtMessage)
        txtOp = findViewById(R.id.operationText)

        val restartB: Button = findViewById(R.id.restart)
        val addB: Button = findViewById(R.id.addition)
        val subB: Button = findViewById(R.id.subtraction)
        val mulB: Button = findViewById(R.id.multiplication)
        val divB: Button = findViewById(R.id.division)
        val num1B: Button = findViewById(R.id.num1)
        val num2B: Button = findViewById(R.id.num2)
        val num3B: Button = findViewById(R.id.num3)
        val num4B: Button = findViewById(R.id.num4)
        val num5B: Button = findViewById(R.id.num5)
        val num6B: Button = findViewById(R.id.num6)
        val confirmB: Button = findViewById(R.id.confirm)
        val delB: Button = findViewById(R.id.del)
        val finishB: Button = findViewById(R.id.finish)

        finishB.isEnabled= false

        //restartB.callOnClick()
        restartB.setOnClickListener(this)
        addB.setOnClickListener(this)
        subB.setOnClickListener(this)
        mulB.setOnClickListener(this)
        divB.setOnClickListener(this)
        num1B.setOnClickListener(this)
        num2B.setOnClickListener(this)
        num3B.setOnClickListener(this)
        num4B.setOnClickListener(this)
        num5B.setOnClickListener(this)
        num6B.setOnClickListener(this)
        confirmB.setOnClickListener(this)
        delB.setOnClickListener(this)
        finishB.setOnClickListener(this)

        num1B.isEnabled = false
        num2B.isEnabled = false
        num3B.isEnabled = false
        num4B.isEnabled = false
        num5B.isEnabled = false
        num6B.isEnabled = false
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View) { //?
        val num1B: Button = findViewById(R.id.num1)
        val num2B: Button = findViewById(R.id.num2)
        val num3B: Button = findViewById(R.id.num3)
        val num4B: Button = findViewById(R.id.num4)
        val num5B: Button = findViewById(R.id.num5)
        val num6B: Button = findViewById(R.id.num6)
        val finishB: Button = findViewById(R.id.finish)

        when (p0.id) {
            R.id.restart -> {
                finishB.text = ""
                finishB.isEnabled = false

                point = -1
                turnLeft = 6

                targetNum = notPrime3digitRandom()
                targetNumS = targetNum.toString()
                //guessNumbers = mutableListOf<Int>()

                guessNumbers.clear()
                // generate one digit number and add to the list 5 times
                for (i in 0..4) {
                    var numberOneDigit = (1..9).random()
                    guessNumbers.add(numberOneDigit)
                }

                // generate 2 digit number one time
                guessNumbers.add((10..99).random())

                txtHello.text = "Target number: $targetNumS"
                txtHidden.text = guessNumbers.toString()

                txtOp.text = ""

                num1B.isEnabled = true
                num2B.isEnabled = true
                num3B.isEnabled = true
                num4B.isEnabled = true
                num5B.isEnabled = true
                num6B.isEnabled = true

                changeNumButton(turnLeft)
                val restartB: Button = findViewById(R.id.restart)
                restartB.text = "Restart"
            }
            R.id.addition -> {
                if (operatorTimeFlag == true && numTimeFlag == false) {
                    opString += " + "
                    operator = 0
                    operandNum += 1
                    operatorTimeFlag = false
                    numTimeFlag = true
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.subtraction -> {
                if (operatorTimeFlag == true && numTimeFlag == false) {
                    opString += " - "
                    operator = 1
                    operandNum += 1
                    operatorTimeFlag = false
                    numTimeFlag = true
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.multiplication -> {
                if (operatorTimeFlag == true && numTimeFlag == false) {
                    opString += " * "
                    operator = 2
                    operandNum += 1
                    operatorTimeFlag = false
                    numTimeFlag = true
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.division -> {
                if (operatorTimeFlag == true && numTimeFlag == false) {
                    opString += " / "
                    operator = 3
                    operandNum += 1
                    operatorTimeFlag = false
                    numTimeFlag = true
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.num1 -> {
                if (operatorTimeFlag == false && numTimeFlag == true && operandIndex != 0) {
                    opString += guessNumbers[0].toString()
                    if (operandNum == 1) {
                        operand1 = guessNumbers[0]
                        operandIndex = 0
                        operatorTimeFlag = true
                        numTimeFlag = false
                    } else if (operandNum == 2) {
                        operand2 = guessNumbers[0]
                        operandIndex = 0
                        operatorTimeFlag = false
                        numTimeFlag = false
                        opFinishFlag = true
                    }
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.num2 -> {
                if (operatorTimeFlag == false && numTimeFlag == true && operandIndex != 1) {
                    opString += guessNumbers[1].toString()
                    if (operandNum == 1) {
                        operand1 = guessNumbers[1]
                        operandIndex = 1
                        operatorTimeFlag = true
                        numTimeFlag = false
                    } else if (operandNum == 2) {
                        operand2 = guessNumbers[1]
                        operandIndex = 1
                        operatorTimeFlag = false
                        numTimeFlag = false
                        opFinishFlag = true
                    }
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.num3 -> {
                if (operatorTimeFlag == false && numTimeFlag == true && operandIndex != 2) {
                    opString += guessNumbers[2].toString()
                    if (operandNum == 1) {
                        operand1 = guessNumbers[2]
                        operandIndex = 2
                        operatorTimeFlag = true
                        numTimeFlag = false
                    } else if (operandNum == 2) {
                        operand2 = guessNumbers[2]
                        operandIndex = 2
                        operatorTimeFlag = false
                        numTimeFlag = false
                        opFinishFlag = true
                    }
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.num4 -> {
                if (operatorTimeFlag == false && numTimeFlag == true && operandIndex != 3) {
                    opString += guessNumbers[3].toString()
                    if (operandNum == 1) {
                        operand1 = guessNumbers[3]
                        operandIndex = 3
                        operatorTimeFlag = true
                        numTimeFlag = false
                    } else if (operandNum == 2) {
                        operand2 = guessNumbers[3]
                        operandIndex = 3
                        operatorTimeFlag = false
                        numTimeFlag = false
                        opFinishFlag = true
                    }
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.num5 -> {
                if (operatorTimeFlag == false && numTimeFlag == true && operandIndex != 4) {
                    opString += guessNumbers[4].toString()
                    if (operandNum == 1) {
                        operand1 = guessNumbers[4]
                        operandIndex = 4
                        operatorTimeFlag = true
                        numTimeFlag = false
                    } else if (operandNum == 2) {
                        operand2 = guessNumbers[4]
                        operandIndex = 4
                        operatorTimeFlag = false
                        numTimeFlag = false
                        opFinishFlag = true
                    }
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.num6 -> {
                if (operatorTimeFlag == false && numTimeFlag == true && operandIndex != 5) {
                    opString += guessNumbers[5].toString()
                    if (operandNum == 1) {
                        operand1 = guessNumbers[5]
                        operandIndex = 5
                        operatorTimeFlag = true
                        numTimeFlag = false
                    } else if (operandNum == 2) {
                        operand2 = guessNumbers[5]
                        operandIndex = 5
                        operatorTimeFlag = false
                        numTimeFlag = false
                        opFinishFlag = true
                    }
                    txtOp.text = opString
                } else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.confirm -> {
                if (opFinishFlag == true) {
                    var result = -1
                    if (operator == 0) {
                        result = operand1 + operand2
                    } else if (operator == 1) {
                        result = operand1 - operand2
                    } else if (operator == 2) {
                        result = operand1 * operand2
                    } else if (operator == 3) {
                        result = operand1 / operand2
                    }

                    txtOp.text = result.toString()

                    point = pointCalc(result, targetNum)
                    if (point == 25) {
                        txtOp.text = "congratulations, your point is $point. Start a new game"
                    } else if (point > 0) {
                        if (num2B.isEnabled == false) {
                            txtOp.text = "your point is $point"
                        } else {
                            txtOp.text =
                                "your point is $point. You can continue to guess or start a new game"
                            finishB.isEnabled = true
                            finishB.text = "FINISH GUESSING"
                        }
                    }

                    guessNumbers.remove(operand1)
                    guessNumbers.remove(operand2)
                    guessNumbers.add(result)

                    turnLeft -= 1
                    if (turnLeft == 1) {
                        if (point > 0) {
                            txtOp.text = "congratulations, your point is $point. Start a new game"
                        } else {
                            txtOp.text = "You guessed 5 times. Game over... Start a new game"
                        }
                        num2B.isEnabled = false
                        num2B.text = ""
                        num1B.text = guessNumbers[0].toString()
                    } else {
                        changeNumButton(turnLeft)
                    }
                    operandNum = 1
                    operator = -1
                    operand1 = 0
                    operand2 = 0
                    opString = ""
                    operandIndex = -1
                    operatorTimeFlag = false
                    numTimeFlag = true
                    opFinishFlag = false
                }
                else {
                    txtOp.text = "error"
                    Handler(Looper.getMainLooper()).postDelayed({
                        txtOp.text = opString
                    }, 600)
                }
            }
            R.id.del -> {
                operandNum = 1
                operator = -1
                operand1 = 0
                operand2 = 0
                opString = ""
                txtOp.text = opString.toString()
                operatorTimeFlag = false
                numTimeFlag = true
            }
            R.id.finish -> {
                var result = -1
                if (operator == 0){
                    result = operand1 + operand2
                }
                else if (operator == 1){
                    result = operand1 - operand2
                }
                else if (operator == 2){
                    result = operand1 * operand2
                }
                else if (operator == 3){
                    result = operand1 / operand2
                }

                txtOp.text = "congratulations, your point is $point. Start a new game"
            }
        }
    }

    fun notPrime3digitRandom() : Int {
        val primeNumbers = arrayOf(103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193,
            197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
            317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443,
            449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587,
            593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719,
            727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859,
            863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971)
        var targetNumber = (100..1000).random()
        while (primeNumbers.contains(targetNumber)){
            targetNumber = (100..1000).random()
        }
        return targetNumber
    }

    fun changeNumButton(numLeft: Int) {
        val num1B: Button = findViewById(R.id.num1)
        val num2B: Button = findViewById(R.id.num2)
        val num3B: Button = findViewById(R.id.num3)
        val num4B: Button = findViewById(R.id.num4)
        val num5B: Button = findViewById(R.id.num5)
        val num6B: Button = findViewById(R.id.num6)
        if (numLeft == 6) {
            num1B.text = guessNumbers[0].toString()
            num2B.text = guessNumbers[1].toString()
            num3B.text = guessNumbers[2].toString()
            num4B.text = guessNumbers[3].toString()
            num5B.text = guessNumbers[4].toString()
            num6B.text = guessNumbers[5].toString()
        }
        else if (numLeft == 5) {
            num1B.text = guessNumbers[0].toString()
            num2B.text = guessNumbers[1].toString()
            num3B.text = guessNumbers[2].toString()
            num4B.text = guessNumbers[3].toString()
            num5B.text = guessNumbers[4].toString()
            num6B.text = ""
            num6B.isEnabled = false
        }
        else if (numLeft == 4) {
            num1B.text = guessNumbers[0].toString()
            num2B.text = guessNumbers[1].toString()
            num3B.text = guessNumbers[2].toString()
            num4B.text = guessNumbers[3].toString()
            num5B.text = ""
            num5B.isEnabled = false
        }
        else if (numLeft == 3) {
            num1B.text = guessNumbers[0].toString()
            num2B.text = guessNumbers[1].toString()
            num3B.text = guessNumbers[2].toString()
            num4B.text = ""
            num4B.isEnabled = false
        }
        else if (numLeft == 2) {
            num1B.text = guessNumbers[0].toString()
            num2B.text = guessNumbers[1].toString()
            num3B.text = ""
            num3B.isEnabled = false
        }
    }

    fun pointCalc(result: Int, targetNum: Int): Int {
        return if (result - targetNum == 0) {
            25
        } else if (result - targetNum == 1 || targetNum - result == 1) {
            println("The number you found is " + 1 + " less or more than the number you want to find.")
            20
        } else if (result - targetNum == 2 || targetNum - result == 2) {
            println("The number you found is " + 2 + " less or more than the number you want to find.")
            15
        } else if (result - targetNum == 3 || targetNum - result == 3) {
            println("The number you found is " + 3 + " less or more than the number you want to find.")
            10
        } else if (result - targetNum == 4 || targetNum - result == 4) {
            println("The number you found is " + 4 + " less or more than the number you want to find.")
            5
        } else {
            0
        }
    }

}