create or replace function NEWID return varchar2
is
begin
  return lower(rawtohex(sys_guid()));
end;