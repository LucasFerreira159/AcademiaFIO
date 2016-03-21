package andersonsaturnino.com.br.academia_v20.Modelo;

/**
 * Created by AndersonLuizRamos on 29/02/2016.
 */
public class Agendamentos {

    private int codigo;
    private Treinos treino;
    private Semanas semana;
    private int serie;
    private int repeticao;
    private int peso;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public Semanas getSemana() {
        return semana;
    }

    public void setSemana(Semanas semana) {
        this.semana = semana;
    }

    public Treinos getTreino() {
        return treino;
    }

    public void setTreino(Treinos treino) {
        this.treino = treino;
    }
}
