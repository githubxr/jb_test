 SELECT
	'REF_NO' AS COLUMN_NAME,
	COUNT(CASE WHEN REF_NO IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN REF_NO IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'UDF_TYPE' AS COLUMN_NAME,
	COUNT(CASE WHEN UDF_TYPE IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN UDF_TYPE IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'DECLARE_NO' AS COLUMN_NAME,
	COUNT(CASE WHEN DECLARE_NO IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN DECLARE_NO IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'DECLARE_METHOD' AS COLUMN_NAME,
	COUNT(CASE WHEN DECLARE_METHOD IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN DECLARE_METHOD IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'PAYER_MENT' AS COLUMN_NAME,
	COUNT(CASE WHEN PAYER_MENT IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN PAYER_MENT IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'PAYEE_MENT' AS COLUMN_NAME,
	COUNT(CASE WHEN PAYEE_MENT IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN PAYEE_MENT IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'EXCHANGE_CODE' AS COLUMN_NAME,
	COUNT(CASE WHEN EXCHANGE_CODE IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN EXCHANGE_CODE IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'FORWARD_TRADING' AS COLUMN_NAME,
	COUNT(CASE WHEN FORWARD_TRADING IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN FORWARD_TRADING IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'EXCHANGE_PURPOSE' AS COLUMN_NAME,
	COUNT(CASE WHEN EXCHANGE_PURPOSE IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN EXCHANGE_PURPOSE IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'TRAN_CODE1' AS COLUMN_NAME,
	COUNT(CASE WHEN TRAN_CODE1 IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN TRAN_CODE1 IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'TRAN_POSTSCRIPT1' AS COLUMN_NAME,
	COUNT(CASE WHEN TRAN_POSTSCRIPT1 IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN TRAN_POSTSCRIPT1 IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'TRAN_CODE2' AS COLUMN_NAME,
	COUNT(CASE WHEN TRAN_CODE2 IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN TRAN_CODE2 IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'TRAN_POSTSCRIPT2' AS COLUMN_NAME,
	COUNT(CASE WHEN TRAN_POSTSCRIPT2 IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN TRAN_POSTSCRIPT2 IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'DECL_AMT1' AS COLUMN_NAME,
	COUNT(CASE WHEN DECL_AMT1 IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN DECL_AMT1 IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'DECL_AMT2' AS COLUMN_NAME,
	COUNT(CASE WHEN DECL_AMT2 IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN DECL_AMT2 IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'CONTRACT_NO' AS COLUMN_NAME,
	COUNT(CASE WHEN CONTRACT_NO IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN CONTRACT_NO IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'INVOICE_NO' AS COLUMN_NAME,
	COUNT(CASE WHEN INVOICE_NO IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN INVOICE_NO IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'ID_CODE' AS COLUMN_NAME,
	COUNT(CASE WHEN ID_CODE IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN ID_CODE IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'ID_NUMBER' AS COLUMN_NAME,
	COUNT(CASE WHEN ID_NUMBER IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN ID_NUMBER IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'REMITTER_TYPE' AS COLUMN_NAME,
	COUNT(CASE WHEN REMITTER_TYPE IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN REMITTER_TYPE IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'REMITTER_NAME' AS COLUMN_NAME,
	COUNT(CASE WHEN REMITTER_NAME IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN REMITTER_NAME IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'APPLICANT' AS COLUMN_NAME,
	COUNT(CASE WHEN APPLICANT IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN APPLICANT IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'APPLICANT_TELL' AS COLUMN_NAME,
	COUNT(CASE WHEN APPLICANT_TELL IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN APPLICANT_TELL IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'RECORD_NUMBER' AS COLUMN_NAME,
	COUNT(CASE WHEN RECORD_NUMBER IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN RECORD_NUMBER IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'IS_BONDED_PAY' AS COLUMN_NAME,
	COUNT(CASE WHEN IS_BONDED_PAY IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN IS_BONDED_PAY IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'PAYEE_COUNTRY' AS COLUMN_NAME,
	COUNT(CASE WHEN PAYEE_COUNTRY IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN PAYEE_COUNTRY IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'OPTION' AS COLUMN_NAME,
	COUNT(CASE WHEN OPTION IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN OPTION IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
UNION ALL
 SELECT
	'UPDATE_REASON' AS COLUMN_NAME,
	COUNT(CASE WHEN UPDATE_REASON IS NULL THEN 1 END) AS IS_NULL,
	COUNT(CASE WHEN UPDATE_REASON IS NOT NULL THEN 1 END) AS IS_NOT_NULL
FROM pt_tran_udf_msg
;
