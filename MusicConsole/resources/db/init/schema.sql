CREATE TABLE IF NOT EXISTS AccountUtente
(
    `Nickname`        varchar(50) NOT NULL,
    `Password`  char(40) NOT NULL,
    PRIMARY KEY (`Nickname`)
);
/*CREATE TABLE IF NOT EXISTS SCAN
(
    `greenPass`    varchar(100) NOT NULL,
    `dataVerifica` timestamp    NOT NULL DEFAULT current_timestamp,
    `valido`       varchar(100) NOT NULL,
    PRIMARY KEY (`greenPass`, `dataVerifica`)
    --FOREIGN KEY (`greenPass`) REFERENCES GREEN_PASS (`qrCode`)
);*/