
/*table "PIZZA_TYPE"*/
INSERT INTO "PIZZATYPE" VALUES (newid(), 'Mexican', '300', '500', '730');
INSERT INTO "PIZZATYPE" VALUES (newid(), 'Pepperoni', '320', '530', '720');
INSERT INTO "PIZZATYPE" VALUES (newid(), 'Margarita', '280', '450', '630');
INSERT INTO "PIZZATYPE" VALUES (newid(), 'Cheese', '300', '500', '730');
INSERT INTO "PIZZATYPE" VALUES (newid(), 'Meat', '350', '550', '750');
/

/*table COOK*/
INSERT INTO "COOK" VALUES (newid(), 'James Oliver', 'FREE');
INSERT INTO "COOK" VALUES (newid(), 'Auguste Gusteau', 'BUSY');
INSERT INTO "COOK" VALUES (newid(), 'Alain Ducasse', 'BUSY');
INSERT INTO "COOK" VALUES (newid(), 'Bobby Flay', 'FREE');
INSERT INTO "COOK" VALUES (newid(), ' Gordon Ramsay', 'BUSY');
/

/*table ORDER*/
INSERT INTO "ORDER" VALUES (newid(), 'IN PROGRESS');
INSERT INTO "ORDER" VALUES (newid(), 'OPEN');
INSERT INTO "ORDER" VALUES (newid(), 'READY');
INSERT INTO "ORDER" VALUES (newid(), 'OPEN');
INSERT INTO "ORDER" VALUES (newid(), 'CLOSE');
INSERT INTO "ORDER" VALUES (newid(), 'CLOSE');
INSERT INTO "ORDER" VALUES (newid(), 'IN PROGRESS');
INSERT INTO "ORDER" VALUES (newid(), 'OPEN');
INSERT INTO "ORDER" VALUES (newid(), 'READY');
INSERT INTO "ORDER" VALUES (newid(), 'OPEN');
INSERT INTO "ORDER" VALUES (newid(), 'CLOSE');


/*table PIZZA*/
DECLARE
  cook       VARCHAR2(50);
  pizza_type  VARCHAR2(50);
  ord         VARCHAR2(50);

  CURSOR cook_cur IS SELECT "ID" FROM "COOK";
  CURSOR pizza_type_cur IS SELECT "ID" FROM "PIZZA_TYPE";
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
      INSERT INTO "PIZZA" VALUES (newid(), 'S', pizza_type, 'OPEN', ord, cook);
      INSERT INTO "PIZZA" VALUES (newid(), 'M', pizza_type, 'IN PROGRESS', ord, cook);
      INSERT INTO "PIZZA" VALUES (newid(), 'L', pizza_type, 'READY', ord, cook);
    END;
  END LOOP;
  commit;
  CLOSE cook_cur;
  CLOSE pizza_type_cur;
  CLOSE ord_cur;
END;
/