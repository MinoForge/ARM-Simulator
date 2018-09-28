package main

import "unicode"

func main() {

}

/** Pointer to input line */
var line *rune

const TSIZE = 5

/**
 * Copies the next token in the file to the token_ptr.
 * @param tokpt The previous token. Will be overwritten.
 */
func GetToken(tokpt *rune) {
	//var counter = 1
	var current_token [TSIZE]rune
	var i int
	for unicode.IsSpace(*line) {
		line++
	}
	if unicode.IsDigit(*line) {
		for unicode.IsDigit(*line) {
			current_token[i] = *line
			line++
			i++
		}
	} else if unicode.IsSymbol(*line) {
		if *line == '@' || *line == ':' || *line == ',' {
			var temp rune = *line
			line++
			//	current_token[0] = temp;
			//	if(*line == '='){
			//		current_token[1] = *line;
			//		line++;
			//	}
			//}else{
			//	current_token[0] = *line;
			//	line++;
		}
	} else {
		for !unicode.IsSpace(*line) && !(unicode.IsSymbol(*line) || unicode.IsDigit(*line)) {
			current_token[i] = *line
			i++
			line++
		}
	}
	copy(tokpt, current_token)
	//strncpy(tokpt,current_token,TSIZE);
}
