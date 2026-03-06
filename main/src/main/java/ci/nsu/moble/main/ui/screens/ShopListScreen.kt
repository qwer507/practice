package ci.nsu.moble.main.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ci.nsu.moble.main.vm.ShoppingItem
import ci.nsu.moble.main.vm.ShoppingViewModel

@Composable
fun ShopListScreen(
    modifier: Modifier = Modifier,
    viewModel: ShoppingViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        TextField(
            value = uiState.newItemText,
            label = { Text("Введите имя нового товара") },
            onValueChange = { newText ->
                viewModel.onNewItemTextChanged(newText)
            }
        )
        Button(onClick = {viewModel.addItem()}) {
            Text("Добавить товар")
        }
        LazyColumn {
            items(uiState.items) {item ->
                ProductCell(viewModel,item)
            }
        }

    }
}

@Composable
fun ProductCell(viewModel: ShoppingViewModel, product: ShoppingItem){
    Column(){
        Text(product.name)
        Checkbox(
            checked = product.isBought,
            onCheckedChange = {viewModel.toggleItemBought(product.id)}
            )
        Button(onClick = {viewModel.deleteItem(product.id)}) {
            Text("Удалить товар")
        }
    }
}
