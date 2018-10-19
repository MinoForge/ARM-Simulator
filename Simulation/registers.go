package Simulation

import "fmt"

func main() {
	init_registers()
}

func init_registers() {
	var registers [32]uint64
	registers[3] = 3
	fmt.Println(registers)

}
