import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ourAppCinema.presentation.viewmodel.MoviesViewModel

@Composable
fun FilterScreen(
    onDismiss: () -> Unit,
    onApplyFilters: (String, String, IntRange, Float) -> Unit,
    viewModel: MoviesViewModel
) {
    val (selectedCountry, setSelectedCountry) = remember { mutableStateOf("") }
    val (selectedGenre, setSelectedGenre) = remember { mutableStateOf("") }
    val (selectedYearRange, setSelectedYearRange) = remember { mutableStateOf(1990..2020) }
    val (selectedRating, setSelectedRating) = remember { mutableStateOf(0f) }

    val countries = listOf("Россия", "США", "Германия", "Франция")
    val genres = listOf("Комедия", "Драма", "Триллер")
    val years = (1990..2022).toList()

    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(
                modifier = Modifier
                    .padding(all = 24.dp)
                    .fillMaxWidth()
            ) {
                DropdownMenuField("Country", countries, selectedCountry, setSelectedCountry)
                DropdownMenuField("Genre", genres, selectedGenre, setSelectedGenre)
                YearSlider(selectedYearRange, setSelectedYearRange)
                RatingSlider(selectedRating, setSelectedRating)

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    TextButton(onClick = {
                        onApplyFilters(selectedCountry, selectedGenre, selectedYearRange, selectedRating)
                        onDismiss()
                    }) {
                        Text("Apply")
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenuField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Text(label, style = MaterialTheme.typography.bodyLarge)
    Box(modifier = Modifier.fillMaxWidth().clickable { expanded = true }) {
        Text(
            text = if (selectedOption.isEmpty()) "Select $label" else selectedOption,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(

                    onClick = {
                    onOptionSelected(option)
                    expanded = false
                },
                    text ={   Text(option)}
                )


            }
        }
    }
}

@Composable
fun YearSlider(range: IntRange, setRange: (IntRange) -> Unit) {
    // Implement a range slider if available, or use two sliders for the start and end years.
}

@Composable
fun RatingSlider(currentRating: Float, setRating: (Float) -> Unit) {
    Text("Rating")
    Slider(
        value = currentRating,
        onValueChange = setRating,
        valueRange = 0f..10f,
        steps = 9
    )
}