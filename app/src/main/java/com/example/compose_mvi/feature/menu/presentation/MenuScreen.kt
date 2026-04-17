package com.example.compose_mvi.feature.menu.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose_mvi.core.utils.LogTag
import com.example.compose_mvi.core.utils.logD

@Composable
fun MenuScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        // -------------------------
        // CREATE
        // -------------------------
        Button(
            onClick = {
                logD(LogTag.UI, "메뉴 클릭: CREATE")
                navController.navigate("create")
            },
            modifier = Modifier.fillMaxWidth()

        ) {
            Text("Create")
        }

        // -------------------------
        // READ
        // -------------------------
        Button(
            onClick = {
                logD(LogTag.UI, "메뉴 클릭: READ")
                navController.navigate("read")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Read")
        }

        // -------------------------
        // UPDATE
        // -------------------------
        Button(
            onClick = {
                logD(LogTag.UI, "메뉴 클릭: UPDATE")
                navController.navigate("update")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update")
        }

        // -------------------------
        // DELETE
        // -------------------------
        Button(
            onClick = {
                logD(LogTag.UI, "메뉴 클릭: DELETE")
                navController.navigate("delete")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }

    }
}