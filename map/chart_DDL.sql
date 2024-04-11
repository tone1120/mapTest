/* 
	항구별 평균 수용률
*/
create table working_vessel_cnt
(
    seq number primary key
    , port_cd VARCHAR2(10) -- port_cd를 pk로 사용? (선박 수용량이 부모?)
    , vessel_cnt NUMBER(5,2)
);

/*
	선박 AIS 데이터
	
	PORT_CD - object
	VSL_ID - object
	ATA_DT - datetime64[ns]
	ATB_DT - datetime64[ns]
	ATD_DT - datetime64[ns]
	SHIP_TYPE - object

*/
create table port_waiting_df_improve_plus
(
	seq number primary key
	, port_cd VARCHAR2(10) not null
	, VSL_ID VARCHAR2(50) not null
    , ATA_DT date not null
    , ATB_DT date not null
    , ATD_DT date not null
    , ship_type VARCHAR2(50) not null
);