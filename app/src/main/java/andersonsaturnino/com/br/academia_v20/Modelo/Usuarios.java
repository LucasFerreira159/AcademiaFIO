package andersonsaturnino.com.br.academia_v20.Modelo;

import java.sql.Date;

/**
 * Created by AndersonLuizRamos on 29/02/2016.
 */
public class Usuarios {

    private int codigo;
    private String nome;
    private Date datanasc;
    private String sexo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int id_Usuario) {
        this.codigo = id_Usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
