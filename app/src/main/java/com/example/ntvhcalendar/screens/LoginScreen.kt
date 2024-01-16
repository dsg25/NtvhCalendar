package com.example.ntvhcalendar.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.ntvhcalendar.LoginActivity
import com.example.ntvhcalendar.data.DataStoreManager
import com.example.ntvhcalendar.utils.checkCredentials
import com.example.ntvhcalendar.data.Credentials
import com.example.ntvhcalendar.data.LoadingDataUser
import com.example.ntvhcalendar.data.StorageUserData
import com.example.ntvhcalendar.ui.theme.BaseBGPrimary
import com.example.ntvhcalendar.ui.theme.NtvhBlue
import com.example.ntvhcalendar.ui.theme.NtvhGreen
import com.example.ntvhcalendar.ui.theme.NtvhLightBlue
import com.example.ntvhcalendar.ui.theme.NtvhRed
import com.example.ntvhcalendar.ui.theme.NtvhWhite
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(usersList: MutableState<List<LoadingDataUser>>) {
    Surface(modifier = Modifier.fillMaxSize().background(BaseBGPrimary)) {
        val coroutine = rememberCoroutineScope()

        val context = LocalContext.current

        val storageNameState = remember { mutableStateOf("") }
        val storageLastNameState = remember { mutableStateOf("") }
        val dataStoreManager = DataStoreManager(context)

        val checkUserCredentials = remember {
            mutableStateOf(true)
        }

        LaunchedEffect(key1 = true) {
            dataStoreManager.getSettings().collect { settings ->
                storageNameState.value = settings.localName
                storageLastNameState.value = settings.localLastName
            }
        }
        var credentials by remember { mutableStateOf(Credentials()) }

// *********** Выход из аккаунта ************

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd)
        {
            IconButton(onClick = {
                coroutine.launch {
                    dataStoreManager.saveSettings(
                        StorageUserData(
                            "",
                            "",
                            0
                        )
                    )
                }
                context.startActivity(Intent(context, LoginActivity::class.java))
                (context as Activity).finish()
            }) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Exit",
                    tint = NtvhBlue)
            }
        }
// *********** END ************

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {


            if (!checkUserCredentials.value)   UserError()

            Spacer(modifier = Modifier.height(10.dp))

            if (storageNameState.value.isNotEmpty() && storageLastNameState.value.isNotEmpty())
            {
                credentials.loginName = storageNameState.value
                credentials.loginLastName = storageLastNameState.value
            }

            
            LoginNameField(
                value = storageNameState.value.ifEmpty { credentials.loginName },
                onChange = { data -> credentials = credentials.copy(loginName = data) },
                modifier = Modifier.fillMaxWidth(),
                enabled = storageNameState.value.isEmpty()
            )
            Spacer(modifier = Modifier.height(10.dp))

            LoginLastField(
                value = storageLastNameState.value.ifEmpty { credentials.loginLastName },
                onChange = { data -> credentials = credentials.copy(loginLastName = data) },
                modifier = Modifier.fillMaxWidth(),
                enabled = storageLastNameState.value.isEmpty()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    checkCredentials(
                        credentials,
                        context,
                        dataStoreManager,
                        storageNameState,
                        storageLastNameState,
                        checkUserCredentials,
                        usersList,
                        coroutine,
                    )
                 },
                enabled = credentials.isNotEmpty() || storageNameState.value.isNotEmpty() && storageLastNameState.value.isNotEmpty(),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text("Login", color = NtvhWhite)
            }

        }
    }
}

@Composable
fun LoginNameField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Name",
    placeholder: String = "Enter your name",
    enabled: Boolean
) {


    val focusManager = LocalFocusManager.current

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        enabled = enabled
    )
}

@Composable
fun LoginLastField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Last Name",
    placeholder: String = "Enter your Last Name",
    enabled: Boolean
) {
    val focusManager = LocalFocusManager.current

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        enabled = enabled
    )
}

@Composable
fun UserError () {

    Text(text = "Пользователя не существует", color = NtvhRed)
}


