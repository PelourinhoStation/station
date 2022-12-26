package com.so.tp.fx;

import java.util.Date;

public class Log {
    private Date timestamp;
    private String mensagem;
    private String tipo;

    public Log(String mensagem) {
        this.timestamp = new Date();
        this.mensagem = mensagem;
        this.tipo = tipo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getTipo() {
        return tipo;
    }
}

