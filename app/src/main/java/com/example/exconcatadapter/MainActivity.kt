package com.example.exconcatadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import com.example.exconcatadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    private lateinit var programmingLanguageAdapter : ProgrammingLanguagesAdapter
    private lateinit var tipAdapter: TipAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()
    }

    private fun initRecycleView(){
        programmingLanguageAdapter = ProgrammingLanguagesAdapter()
        tipAdapter = TipAdapter().apply {
            /*quando o item for clicado vamos tratar o que ele vai fazer aqui dentro*/
            gotItItemClickListener = {
                dismissTip()
            }
        }

        /*vamos passar para dentro do RecycleView os adpters que queremos mostrar
        * lembrando que o primeiro a ser incerido será o primeiro mostrado
        * */
        binding.recyclerFeed.adapter = ConcatAdapter(tipAdapter, programmingLanguageAdapter)

        programmingLanguageAdapter.submitList(programmingLanguages)
        tipAdapter.submitList(tips)
    }

    private fun dismissTip(){
        /*
        * quando chamar a função ele vai remover o item que está na possição 0, que é a notificação
        * */
        tipAdapter.notifyItemRemoved(0)
    }
}