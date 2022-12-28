package com.example.composetutorial.users_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.composetutorial.NavigationKeys

@Composable
fun UsersListScreen(navController: NavHostController?) {
    UsersListScreenContent { userId ->
        navController?.navigate("${NavigationKeys.SCREEN.USER_DETAILS_SCREEN}/$userId")
    }
}

@Composable
fun UsersListScreenContent(
    usersList: List<UserProfile> = userProfileList,
    onUserClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar() },
        content = { padding ->
            BottomScreenContent(contentPadding = padding, usersList = usersList) { userId ->
                onUserClick(userId)
            }
        })
}

@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Users List"
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun BottomScreenContent(
    contentPadding: PaddingValues,
    usersList: List<UserProfile>,
    onUserClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(16.dp)
        ) {
            items(usersList) { userProfile ->
                UserItem(userProfile) { onUserClick.invoke(userProfile.id) }
            }
        }
    }
}

@Composable
fun UserItem(userProfile: UserProfile, onUserClick: () -> Unit) {
    val customCardElevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp,
        pressedElevation = 2.dp,
        focusedElevation = 4.dp
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        onClick = { onUserClick.invoke() },
        shape = RoundedCornerShape(6.dp),
        elevation = customCardElevation
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = userProfile.pictureUrl,
                contentDescription = "Content",
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = userProfile.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (userProfile.status) "Online" else "Offline",
                    modifier = Modifier.alpha(0.5f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersListScreenPreview() {
    UsersListScreen(null)
}