
/*table "PIZZA_TYPE"*/
INSERT INTO "PIZZATYPE" VALUES (newid(), 'Sea', '300', '500', '730');
INSERT INTO "PIZZATYPE" VALUES (newid(), 'Pepperoni', '320', '530', '720');
INSERT INTO "PIZZATYPE" VALUES (newid(), 'Margarita', '280', '450', '630');
INSERT INTO "PIZZATYPE" VALUES (newid(), 'ReadSea', '300', '500', '730');
INSERT INTO "PIZZATYPE" VALUES (newid(), 'PepMash', '350', '550', '750');
INSERT INTO "PIZZATYPE" VALUES (newid(), 'Mashrooms', '350', '550', '750');
/

/*table COOK*/
INSERT INTO "COOK" VALUES (newid(), 'James Oliver', 'FREE', 0);
INSERT INTO "COOK" VALUES (newid(), 'Auguste Gusteau', 'BUSY', 2);
INSERT INTO "COOK" VALUES (newid(), 'Alain Ducasse', 'BUSY', 1);
INSERT INTO "COOK" VALUES (newid(), 'Bobby Flay', 'FREE', 0);
INSERT INTO "COOK" VALUES (newid(), ' Gordon Ramsay', 'BUSY', 0);
/

/*table ORDER*/
INSERT INTO "ORDER" VALUES (newid(), 'IN PROGRESS', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'OPEN', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'READY', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'OPEN', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'CLOSE', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'CLOSE', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'IN PROGRESS', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'OPEN', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'READY', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'OPEN', CURRENT_TIMESTAMP);
INSERT INTO "ORDER" VALUES (newid(), 'CLOSE', CURRENT_TIMESTAMP);


/*table PIZZA*/
DECLARE
  cook       VARCHAR2(50);
  pizza_type  VARCHAR2(50);
  ord         VARCHAR2(50);

  CURSOR cook_cur IS SELECT "ID" FROM "COOK";
  CURSOR pizza_type_cur IS SELECT "ID" FROM "PIZZATYPE";
  CURSOR ord_cur IS SELECT "ID" FROM "ORDER";
BEGIN

  OPEN cook_cur;
  OPEN pizza_type_cur;
  OPEN ord_cur;
  LOOP
    FETCH ord_cur INTO ord;
    EXIT WHEN cook_cur%NOTFOUND;
    BEGIN
      FETCH pizza_type_cur INTO pizza_type;
      FETCH cook_cur INTO cook;
      INSERT INTO "PIZZA" VALUES (newid(), 'S', pizzatype, 'OPEN', ord, cook);
      INSERT INTO "PIZZA" VALUES (newid(), 'M', pizzatype, 'IN PROGRESS', ord, cook);
      INSERT INTO "PIZZA" VALUES (newid(), 'L', pizzatype, 'READY', ord, cook);
    END;
  END LOOP;
  commit;
  CLOSE cook_cur;
  CLOSE pizza_type_cur;
  CLOSE ord_cur;
END;
/