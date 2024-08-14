package com.arvind.pardypandataskcomposeapp.screens

import android.app.Activity
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvind.pardypandataskcomposeapp.navigation.Screens
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current

    val phoneNumber = remember { mutableStateOf("") }

    val otp = remember { mutableStateOf("") }

    val verificationID = remember { mutableStateOf("") }

    val message = remember { mutableStateOf("") }

    val maxChar = 10

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance();
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = phoneNumber.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { if (it.length <= maxChar) phoneNumber.value = it },
            placeholder = { Text(text = "Enter your phone number") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (TextUtils.isEmpty(phoneNumber.value.toString())) {
                    Toast.makeText(context, "Please enter phone number..", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    val number = "+91${phoneNumber.value}"
                    sendVerificationCode(number, mAuth, context as Activity, callbacks)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Generate OTP",
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


        TextField(
            value = otp.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { otp.value = it },
            placeholder = { Text(text = "Enter your otp") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (TextUtils.isEmpty(otp.value.toString())) {
                    Toast.makeText(context, "Please enter your otp..", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        verificationID.value, otp.value
                    )
                    // on below line signing within credentials
                    signInWithPhoneAuthCredential(
                        credential,
                        mAuth,
                        context as Activity,
                        context,
                        message,
                        navController
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Verify OTP",
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message.value,
            style = TextStyle(color = Color.Green, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )

    }

    callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            message.value = "Verification successful"
            Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            message.value = "Fail to verify user : \n" + p0.message
            Toast.makeText(context, "Verification failed..", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationId, p1)
            verificationID.value = verificationId
        }
    }
}

fun signInWithPhoneAuthCredential(
    credential: PhoneAuthCredential,
    auth: FirebaseAuth,
    activity: Activity,
    context: Activity,
    message: MutableState<String>,
    navController: NavController
) {
    auth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                message.value = "Verification successful"
                navController.popBackStack()
                navController.navigate(Screens.HomeScreen.route)

                Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
            } else {
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(
                        context,
                        "Verification failed.." + (task.exception as FirebaseAuthInvalidCredentialsException).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
}

fun sendVerificationCode(
    number: String,
    auth: FirebaseAuth,
    activity: Activity,
    callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
) {
    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(number)
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(activity)
        .setCallbacks(callbacks)
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}
