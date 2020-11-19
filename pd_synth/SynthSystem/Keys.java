	package SynthSystem;

public enum Keys {
	C(0),
	CS(1),
	D(2),
	DS(3),
	E(4),
	F(5),
	FS(6),
	G(7),
	GS(8),
	A(9),
	AS(10),
	H(11),
	C1(12),
	C1S(13),
	D1(14),
	D1S(15),
	E1(16),
	F1(17),
	F1S(18),
	G1(19),
	G1S(20),
	A1(21),
	A1S(22),
	H1(23);
	private int value;
	private Keys(int value) {
		this.value = value;
	}
	public int getValue() {
        return value;
    }
}
