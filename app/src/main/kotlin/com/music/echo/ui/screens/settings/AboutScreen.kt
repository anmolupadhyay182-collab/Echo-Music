@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package echo.music.iad1tya.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateBottomPadding
import androidx.compose.foundation.layout.calculateTopPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import echo.music.iad1tya.BuildConfig
import echo.music.iad1tya.LocalPlayerAwareWindowInsets
import echo.music.iad1tya.R
import echo.music.iad1tya.ui.component.IconButton
import echo.music.iad1tya.ui.utils.backToMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
  navController: NavController,
  scrollBehavior: TopAppBarScrollBehavior,
  onBack: (() -> Unit)? = null,
  highlightKey: String? = null,
) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = MaterialTheme.colorScheme.surface,
    contentWindowInsets = WindowInsets(0, 0, 0, 0),
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = stringResource(R.string.about),
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
        },
        navigationIcon = {
          IconButton(
            onClick = { onBack?.invoke() ?: navController.navigateUp() },
            onLongClick = navController::backToMain,
          ) {
            androidx.compose.material3.Icon(painterResource(R.drawable.arrow_back), contentDescription = null)
          }
        },
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.surface,
          scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
        scrollBehavior = scrollBehavior,
      )
    },
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier.fillMaxSize().windowInsetsPadding(
        LocalPlayerAwareWindowInsets.current.only(WindowInsetsSides.Horizontal),
      ),
      contentPadding = PaddingValues(
        start = 16.dp,
        top = innerPadding.calculateTopPadding() + 8.dp,
        end = 16.dp,
        bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding() + 32.dp,
      ),
      verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
      item { AboutAppCard() }
      item {
        Column(
          modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Text(
            text = "Developer",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
          )
          Text(
            text = "Anmol Upadhyay",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }
    }
  }
}

@Composable
private fun AboutAppCard() {
  Column(
    modifier = Modifier.fillMaxWidth().padding(vertical = 28.dp, horizontal = 20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    Image(
      painter = painterResource(R.drawable.ic_launcher_nobg),
      contentDescription = null,
      colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
      modifier = Modifier.size(100.dp),
    )
    Text(
      text = "Fyxe Music",
      style = MaterialTheme.typography.titleLarge,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.onSurface,
    )
    Text(
      text = BuildConfig.VERSION_NAME,
      style = MaterialTheme.typography.labelSmall,
      color = MaterialTheme.colorScheme.primary,
    )
  }
}
