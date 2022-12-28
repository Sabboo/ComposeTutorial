package com.example.composetutorial.user_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.composetutorial.users_list.UserProfile
import com.example.composetutorial.users_list.userProfileList

@Composable
fun UserDetailsScreen(navController: NavHostController?, userID: Int?) {
    val userProfile = userProfileList.first { it.id == userID }
    val customCardElevation = CardDefaults.cardElevation(
        defaultElevation = 8.dp,
        pressedElevation = 2.dp,
        focusedElevation = 4.dp
    )

    Scaffold(
        topBar = {
            UserDetailsScreenTopAppBar(
                title = "User Details",
                icon = Icons.Filled.ArrowBack,
                iconClickAction = { navController?.navigateUp() },
                contentDescription = "ArrowBack"
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        UserDetailsScreenContent(userProfile, customCardElevation, paddingValues)
    }
}

@Composable
fun UserDetailsScreenTopAppBar(
    title: String,
    icon: ImageVector,
    iconClickAction: () -> Unit,
    contentDescription: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.clickable { iconClickAction.invoke() }
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun UserDetailsScreenContent(
    userProfile: UserProfile,
    customCardElevation: CardElevation,
    paddingValues: PaddingValues
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            shape = RoundedCornerShape(6.dp),
            elevation = customCardElevation
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = userProfile.pictureUrl,
                    contentDescription = "Content",
                    modifier = Modifier
                        .size(256.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                )
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
    UserDetailsScreen(null, userProfileList[0].id)
}