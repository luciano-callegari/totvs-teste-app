-- Table Definition ----------------------------------------------
CREATE TABLE conta (
    id SERIAL PRIMARY KEY,
    data_criacao timestamp,
    data_alteracao timestamp,
    data_vencimento date,
    data_pagamento date,
    valor numeric(13,2),
    descricao text,
    situacao varchar(50)
);

-- Indices -------------------------------------------------------
CREATE INDEX ix_conta_dt_venc_situacao ON conta(data_vencimento,situacao);
CREATE INDEX ix_conta_dt_venc_descricao ON conta(data_vencimento,descricao);
