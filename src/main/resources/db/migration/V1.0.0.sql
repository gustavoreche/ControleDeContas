CREATE TABLE `contas_a_pagar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `valor_original` DOUBLE NOT NULL,
  `data_vencimento` DATE NOT NULL,
  `data_pagamento` DATE NOT NULL,
  `valor_corrigido` DOUBLE NOT NULL,
  `dias_de_atraso` INT NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO contas_a_pagar (id, nome, valor_original, data_vencimento, data_pagamento, valor_corrigido, dias_de_atraso) VALUES (1, 'conta de luz', 50, '2021-04-29', '2021-04-28', 0.0, 0);
INSERT INTO contas_a_pagar (id, nome, valor_original, data_vencimento, data_pagamento, valor_corrigido, dias_de_atraso) VALUES (2, 'conta de agua', 28.50, '2021-04-29', '2021-05-01', 29.00, 2);
INSERT INTO contas_a_pagar (id, nome, valor_original, data_vencimento, data_pagamento, valor_corrigido, dias_de_atraso) VALUES (3, 'fatura do cartao', 100, '2021-04-29', '2021-05-03', 101, 4);
INSERT INTO contas_a_pagar (id, nome, valor_original, data_vencimento, data_pagamento, valor_corrigido, dias_de_atraso) VALUES (4, 'conta de celular', 33, '2021-04-29', '2021-05-07', 35, 8);

CREATE TABLE `regra_de_multa_por_conta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dias_de_atraso` INT NOT NULL,
  `valor_original` DOUBLE NOT NULL,
  `porcentagem_multa` DOUBLE NOT NULL,
  `valor_com_multa` DOUBLE NOT NULL,
  `porcentagem_juros` DOUBLE NOT NULL,
  `valor_juros_por_dia` DOUBLE NOT NULL,
  `valor_corrigido` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));
  