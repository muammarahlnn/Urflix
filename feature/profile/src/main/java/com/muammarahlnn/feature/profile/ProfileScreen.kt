package com.muammarahlnn.feature.profile

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.muammarahlnn.urflix.core.designsystem.icon.UrflixIcons
import com.muammarahlnn.urflix.core.designsystem.util.noRippleClickable
import com.muammarahlnn.urflix.feature.profile.R


/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file ProfileScreen, 02/11/2023 09.33 by Muammar Ahlan Abimanyu
 */
@Composable
internal fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    var showEditProfileDataDialog by rememberSaveable { mutableStateOf(false) }
    ProfileScreen(
        showEditProfileDataDialog = showEditProfileDataDialog,
        fullName = viewModel.fullName,
        email = viewModel.email,
        onEditDataClick = {
            showEditProfileDataDialog = true
        },
        onFullNameChange = viewModel::setFullNameValue,
        onEmailChange = viewModel::setEmailValue,
        onChangeChangeProfileDataDialog = {
            viewModel.saveUser()
            showEditProfileDataDialog = false
            Toast.makeText(
                context,
                "Your profile data is successfully updated",
                Toast.LENGTH_SHORT
            ).show()
        },
        onDismissChangeProfileDataDialog = {
            showEditProfileDataDialog = false
        },
        modifier = modifier,
    )
}

@Composable
private fun ProfileScreen(
    showEditProfileDataDialog: Boolean,
    fullName: String,
    email: String,
    onEditDataClick: () -> Unit,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onChangeChangeProfileDataDialog: () -> Unit,
    onDismissChangeProfileDataDialog: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (showEditProfileDataDialog) {
        EditProfileDataDialog(
            fullName = fullName,
            email = email,
            onFullNameChange = onFullNameChange,
            onEmailChange = onEmailChange,
            onChange = onChangeChangeProfileDataDialog,
            onDismiss = onDismissChangeProfileDataDialog,
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        ProfileData(
            fullName = fullName,
            email = email,
            onEditDataClick = onEditDataClick,
        )
    }
}

@Composable
private fun ProfileData(
    fullName: String,
    email: String,
    onEditDataClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        ProfileDetailInfoCard(
            fullName = fullName,
            email = email,
            onEditDataClick = onEditDataClick,
            modifier = Modifier.padding(top = photoProfileSize / 2)
        )
        PhotoProfile(
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun ProfileDetailInfoCard(
    fullName: String,
    email: String,
    onEditDataClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = photoProfileSize / 2 + 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = UrflixIcons.Info,
                        contentDescription = stringResource(id = R.string.profile_info),
                        tint = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(id = R.string.profile_info),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.noRippleClickable {
                        onEditDataClick()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.edit_data),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = UrflixIcons.Edit,
                        contentDescription = stringResource(id = R.string.edit_data),
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            BaseProfileInfoText(
                title = stringResource(id = R.string.full_name),
                value = fullName.ifEmpty { stringResource(id = R.string.guest_full_name) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            BaseProfileInfoText(
                title = stringResource(id = R.string.email),
                value = email.ifEmpty { stringResource(id = R.string.guest_email) }
            )
        }
    }
}

@Composable
private fun PhotoProfile(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(photoProfileSize)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
        )

        FilledIconButton(
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ),
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(32.dp)
        ) {
            Icon(
                imageVector = UrflixIcons.Camera,
                contentDescription = stringResource(id = R.string.change_photo_profile),
            )
        }
    }
}

@Composable
private fun BaseProfileInfoText(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    vertical = 16.dp,
                    horizontal = 16.dp,
                )
        )
    }
}

@Composable
private fun EditProfileDataDialog(
    fullName: String,
    email: String,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onChange: () -> Unit,
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.change_profile_data),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                EditableOutlinedTextField(
                    title = stringResource(id = R.string.full_name),
                    value = fullName,
                    onValueChange = onFullNameChange
                )
                Spacer(modifier = Modifier.height(16.dp))

                EditableOutlinedTextField(
                    title = stringResource(id = R.string.email),
                    value = email,
                    onValueChange = onEmailChange,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (fullName.isEmpty()) {
                        Toast.makeText(context, "Full name can't be empty", Toast.LENGTH_SHORT).show()
                        return@TextButton
                    }
                    if (email.isEmpty()) {
                        Toast.makeText(context, "Email can't be empty", Toast.LENGTH_SHORT).show()
                        return@TextButton
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(context, "The filled email is not an email", Toast.LENGTH_SHORT).show()
                        return@TextButton
                    }
                    onChange()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.change),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
private fun EditableOutlinedTextField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(2.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onValueChange("")
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear",
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodySmall,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.onBackground
            ),
        )
    }
}

private val photoProfileSize = 100.dp