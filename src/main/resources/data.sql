insert into tb_pre_cadastro (cd_pre_cadastro, dt_hr_solicitacao,dt_nascimento,ds_email,nm_pessoa,tp_status) values('e38dae31-5859-40a8-a2e2-d7d1a1832a9b','2023-10-17 23:20', '2023-10-17','fulano@fulano.com', 'Naruto Uchiha Sarado','VALIDADO');
insert into tb_pre_cadastro (cd_pre_cadastro, dt_hr_solicitacao,dt_nascimento,ds_email,nm_pessoa,tp_status) values('e38dae31-5859-40a8-a2e2-d7d1a1832a9a','2023-10-24 22:30', '2023-10-17','fulano2@fulano.com', 'Naruto Uchiha Maromba','REVALIDAR');
insert into tb_documento_cpf (cd_documento, cd_pre_cadastro)  values('33217461827','e38dae31-5859-40a8-a2e2-d7d1a1832a9b');
insert into tb_documento_rg (cd_documento, cd_pre_cadastro)  values('999666777','e38dae31-5859-40a8-a2e2-d7d1a1832a9b');
insert into tb_documento_cpf (cd_documento, cd_pre_cadastro)  values('33217461828','e38dae31-5859-40a8-a2e2-d7d1a1832a9a');
insert into tb_documento_rg (cd_documento, cd_pre_cadastro)  values('999666778','e38dae31-5859-40a8-a2e2-d7d1a1832a9a');