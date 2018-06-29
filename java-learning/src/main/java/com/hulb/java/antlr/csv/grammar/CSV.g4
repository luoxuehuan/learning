

grammar CSV;

file:hdr row+ ;
hdr :row;

row :field (',' field)*;


field:
|INT
|STRING
;
