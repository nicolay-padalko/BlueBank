ALTER TABLE tb_transacao ADD COLUMN conta_destino_id INT(9) NOT NULL DEFAULT 0;

UPDATE tb_transacao SET conta_destino_id = 0 WHERE id = 1;
UPDATE tb_transacao SET conta_destino_id = 5001 WHERE id = 2;
UPDATE tb_transacao SET conta_destino_id = 0 WHERE id = 3;