package com.example.whatsappclone.presentation.signinScreen


import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.animelist.common.Constants
import com.example.whatsappclone.ui.theme.Green

//      supportingText = { if(phoneNum.length!=10)Text(text = "enter 10 digit number",color = Color.Red) },




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signIn(viewModel: SignInViewModel, activity: Activity,navController: NavController) {
    var phoneNum by remember { mutableStateOf("") }
    var countryCode by remember { mutableStateOf("91") }
    val interactionSourceForPhoneNum = remember { MutableInteractionSource() }
    val interactionSourceForCountryCode = remember { MutableInteractionSource() }
    val density = LocalDensity.current
    var textFieldWidth by remember { mutableStateOf(0.dp) }

    val isCountryCodeFocused by interactionSourceForCountryCode.collectIsFocusedAsState()
    val isPhoneNumFocused by interactionSourceForPhoneNum.collectIsFocusedAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .imePadding(), horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Enter your phone number",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .height(100.dp)
                .width(270.dp)          //SIZE
                .onGloballyPositioned { coordinates ->
                    textFieldWidth = with(density) {
                        coordinates.size.width.toDp()
                    }
                },
            textAlign = TextAlign.Center,
        )
        BasicTextField(
            value = "India",
            onValueChange = {
            },
            modifier = Modifier
                .widthIn(max = textFieldWidth)
                .height(36.dp),

            enabled = true,
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
            ),
            readOnly = true,

            decorationBox = { innerTextField ->
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            color = Green,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 4f
                        )
                    }
                    .absolutePadding(bottom = 2.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {}
                    ),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center


                ){
                    Spacer(modifier = Modifier.weight(1f))
                    innerTextField()
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier

                            .height(20.dp)
                        ,
                        tint = Green
                    )
                }

            }
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.widthIn(min = textFieldWidth, max = textFieldWidth)
        ) {
            BasicTextField(
                value = countryCode,
                onValueChange = { countryCode = it },
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .height(45.dp),
                interactionSource = interactionSourceForCountryCode,
                enabled = true,
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp
                ),
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier
                        .drawBehind {
                            drawLine(
                                color = Green,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = if (isCountryCodeFocused) 8f else 4f
                            )
                        }
                        .absolutePadding(bottom = 2.dp), verticalAlignment = Alignment.Bottom
                    ) {

                        Icon(
                            imageVector = Icons.Filled.Add, contentDescription = null,
                            Modifier
                                .size(17.dp), tint = Color.Gray
                        )
                        innerTextField()
                    }
                }
            )
            BasicTextField(
                value = phoneNum,
                onValueChange = { phoneNum = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Phone
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)

                    .height(45.dp),
                interactionSource = interactionSourceForPhoneNum,
                enabled = true,
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp
                ),

                decorationBox = { innerTextField ->

                    Box(modifier = Modifier
                        .drawBehind {
                            drawLine(
                                color = Green,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = if (isPhoneNumFocused) 8f else 4f
                            )
                        }
                        .absolutePadding(bottom = 2.dp),
                        contentAlignment = Alignment.BottomStart) {

                    if (phoneNum.equals("")){
                        Text(text = "phone number",color = Color.Gray)
                    }

                        innerTextField()




                    }
                })
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            viewModel.getOtpCase(phoneNum, activity)
            navController.navigate(Constants.OTP_ROUTE)
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Green
        )) {
            Text("Next", color = Color.White)
        }

    }
}

