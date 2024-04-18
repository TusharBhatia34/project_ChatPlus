@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.whatsappclone.presentation.signinScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whatsappclone.ui.theme.Green

@Composable
fun otp(viewModel: SignInViewModel,navController: NavController) {
    var otp by remember {mutableStateOf("") }
 Column (
     modifier = Modifier
         .fillMaxSize()
         .background(Color.White),
     verticalArrangement = Arrangement.Top,
     horizontalAlignment = Alignment.CenterHorizontally
 ){
     Text(text = "Verify your number",color = Green, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
     Spacer(modifier = Modifier.height(100.dp))
          BasicTextField(
              value = otp,
              onValueChange = { otp = it },

              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
              decorationBox = {
                  Row(horizontalArrangement = Arrangement.Center) {
                      repeat(6){ index->
                          var char = when{
                              index >=otp.length-> "\u2212"
                              else ->otp.get(index).toString()
                          }
                          Text(text = char, fontSize = 35.sp)
                          Spacer(modifier = Modifier.width(8.dp))

                      }
                  }

              }
          )


//     Button(onClick = {
//viewModel.verifyOtp(otp)
//                              navController.navigate(Constants.CHAT_LIST_ROUTE)
//     }
//         ,colors = ButtonDefaults.buttonColors(
//             containerColor = Green
//         )) {
//         Text("Next",color = Color.White)
//     }
 }
}
