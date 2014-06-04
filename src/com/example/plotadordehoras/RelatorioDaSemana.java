package com.example.plotadordehoras;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import model.Atividade;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
 
import com.projetopiloto.plotadordehoras.util.CountriesList;
import com.projetopiloto.plotadordehoras.util.GerarRelatorioDaSemana;
import com.projetopiloto.plotadordehoras.util.PieGraph;
 
public class RelatorioDaSemana extends Activity {
 
private ListView listView;
private static GerarRelatorioDaSemana gerarRelatorio;
private Button gerarGrafico;
 
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_relatorio_da_semana);
 
/**
* Depois que tiver concertado o ManipulaBD descomenta a linha 36
* [Lucas]
*/
 gerarRelatorio= new GerarRelatorioDaSemana(this);
 
listView = (ListView) findViewById(R.id.lv_country);
 
// Quando resolver o problema do BD substituir 4 pela quantidade total
// de atividades da semana [Lucas]
listView.setAdapter(new EfficientAdapter(this, getGerarRelatorio().getNomeAtivades().size()));
 
gerarGrafico = (Button) findViewById(R.id.botaoGerarGrafico);
gerarGrafico.setOnClickListener(new OnClickListener() {
 
@Override
public void onClick(View arg0) {
 
Date data = new Date("05/05/2014");
 
Atividade at1 = new Atividade("Correr", 50, data);
Atividade at2 = new Atividade("Estudar", 200, data);
Atividade at3 = new Atividade("Dormir", 80, data);
Atividade at4 = new Atividade("Pedalar", 10, data);
 
ArrayList<Atividade> listaAtividades = new ArrayList<Atividade>();
listaAtividades.add(at1);
listaAtividades.add(at2);
listaAtividades.add(at3);
listaAtividades.add(at4);
 
// Quando resolver o problema do ManipulaBD substituir
// listaAtividades por
// gerarRelatorio.getAtividades(DataIncio,DataFim) [Lucas]
 
PieGraph pie = new PieGraph();
 
Intent intent = pie.getIntent(getApplicationContext(),
getGerarRelatorio().getAtividadesOrdenadasDecrescente());
startActivity(intent);
 
}
});
}
 
private static class EfficientAdapter extends BaseAdapter {
 
private LayoutInflater layoutInflater;
private int quantidadeDeAtividades;
 
public EfficientAdapter(Context context, int quantidadeTotalDeAtividades) {
layoutInflater = LayoutInflater.from(context);
quantidadeDeAtividades = quantidadeTotalDeAtividades;
}
 
@Override
public int getCount() {
return quantidadeDeAtividades;
// Aqui tem que ser do tamanho da lista de Atividades [Lucas]
 
}
 
@Override
public Object getItem(int position) {
return position;
}
 
@Override
public long getItemId(int position) {
return position;
}
 
@Override
public View getView(int position, View convertView, ViewGroup parent) {
ViewColuna coluna;
System.out.println(position);
 
GerarRelatorioDaSemana rela = gerarRelatorio;
 
if (convertView == null) {
convertView = layoutInflater
.inflate(R.layout.two_col_row, null);
coluna = new ViewColuna();
coluna.calunaTempoInvestidoTextView = (TextView) convertView
.findViewById(R.id.idTempoInvestido);
coluna.colunaAtividadeTextView = (TextView) convertView
.findViewById(R.id.idAtividade);
 
convertView.setTag(coluna);
} else {
coluna = (ViewColuna) convertView.getTag();
}
 
/**
* Depois que tiver consertado o ManipulaBD descomenta aqui e
* comenta as linhas ate o return [Lucas]
*/
 coluna.calunaTempoInvestidoTextView.setText(String.valueOf(getGerarRelatorio().getTempoAtivades().get(position)));
 coluna.colunaAtividadeTextView.setText(String.valueOf(getGerarRelatorio().getNomeAtivades().get(position)));
 
//Date data = new Date("05/05/2014");
 
//Atividade at1 = new Atividade("Correr", 50, data);
//Atividade at2 = new Atividade("Estudar", 200, data);
//Atividade at3 = new Atividade("Dormir", 80, data);
//Atividade at4 = new Atividade("Pedalar", 10, data);
 
//List<String> nomes = new ArrayList<String>();
//nomes.add(at1.getTitulo());
//nomes.add(at2.getTitulo());
//nomes.add(at3.getTitulo());
//nomes.add(at4.getTitulo());
 
//List<Integer> minutos = new ArrayList<Integer>();
//minutos.add(at1.getTempo());
//minutos.add(at2.getTempo());
//minutos.add(at3.getTempo());
//minutos.add(at4.getTempo());
 
//coluna.calunaTempoInvestidoTextView.setText(minutos.get(position)
//+ "");
//coluna.colunaAtividadeTextView.setText(nomes.get(position) + "");
 
return convertView;
}
 
}
 
static class ViewColuna {
TextView calunaTempoInvestidoTextView;
TextView colunaAtividadeTextView;
}
 
public static GerarRelatorioDaSemana getGerarRelatorio() {
return gerarRelatorio;
}
 
public void setGerarRelatorio(GerarRelatorioDaSemana gerarRelatorio) {
this.gerarRelatorio = gerarRelatorio;
}
}
