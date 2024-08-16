package com.project.ecommerce.enums;

public enum ProductClassification {

    FRUTAS("frutas"),
    PRODUTO_DE_LIMPEZA("produto de limpeza"),
    HIGIENE("higiene"),
    FERRAMENTA("ferramenta"),
    LEGUMES("legumes"),
    CARNES("carnes"),
    LATICINIOS("laticinios"),
    GRANOS("granos"),
    ENLATADOS("enlatados"),
    CONSERVAS("conservas"),
    DOCES("doces"),
    SALGADOS("salgados"),
    PAPELARIA("papelaria"),
    ELETRONICOS("eletronicos");

    private String descricao;

    ProductClassification(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
