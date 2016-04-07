import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ContO {

	Medium m;
	Plane[] p;
	int npl = 0;
	int x = 0;
	int y = 0;
	int z = 0;
	int xz = 0;
	int xy = 0;
	int zy = 0;
	int dist = 0;
	int maxR = 0;
	int disp = 0;
	boolean shadow = false;
	boolean loom = false;
	int grounded = 1;
	boolean colides = false;
	int rcol = 0;
	int pcol = 0;
	boolean out = false;
	boolean fire = false;
	boolean hit = false;
	int nhits = 0;
	int maxhits = -1;
	boolean wire = false;
	boolean exp = false;

	public int ys(int var1, int var2) {
		if (var2 < 10) {
			var2 = 10;
		}

		return (var2 - m.focus_point) * (m.cy - var1) / var2 + var1;
	}

	public void reset() {
		exp = false;
		nhits = 0;
		xz = 0;
		xy = 0;
		zy = 0;
	}

	public ContO(byte[] var1, Medium var2, int var3, int var4, int var5) {
		m = var2;
		p = new Plane[100];
		x = var3;
		y = var4;
		z = var5;
		boolean var8 = false;
		int var9 = 0;
		float var10 = 1.0F;
		int[] var11 = new int[100];
		int[] var12 = new int[100];
		int[] var13 = new int[100];
		int[] var14 = new int[] { 50, 50, 50 };

		try {
			DataInputStream var15 = new DataInputStream(new ByteArrayInputStream(var1));

			String var6;
			while ((var6 = var15.readLine()) != null) {
				String var7 = "" + var6.trim();
				if (var7.startsWith("<p>")) {
					var8 = true;
					var9 = 0;
				}

				if (var8) {
					if (var7.startsWith("c")) {
						var14[0] = getvalue("c", var7, 0);
						var14[1] = getvalue("c", var7, 1);
						var14[2] = getvalue("c", var7, 2);
					}

					if (var7.startsWith("p")) {
						var11[var9] = (int) ((float) getvalue("p", var7, 0) * var10);
						var12[var9] = (int) ((float) getvalue("p", var7, 1) * var10);
						var13[var9] = (int) ((float) getvalue("p", var7, 2) * var10);
						++var9;
					}
				}

				if (var7.startsWith("</p>")) {
					p[npl] = new Plane(m, var11, var13, var12, var9, var14);
					++npl;
					var8 = false;
				}

				if (var7.startsWith("MaxRadius")) {
					maxR = getvalue("MaxRadius", var7, 0);
				}

				if (var7.startsWith("disp")) {
					disp = getvalue("disp", var7, 0);
				}

				if (var7.startsWith("shadow")) {
					shadow = true;
				}

				if (var7.startsWith("loom")) {
					loom = true;
				}

				if (var7.startsWith("out")) {
					out = true;
				}

				if (var7.startsWith("hits")) {
					maxhits = getvalue("hits", var7, 0);
				}

				if (var7.startsWith("colid")) {
					colides = true;
					rcol = getvalue("colid", var7, 0);
					pcol = getvalue("colid", var7, 1);
				}

				if (var7.startsWith("grounded")) {
					grounded = getvalue("grounded", var7, 0);
				}

				if (var7.startsWith("div")) {
					var10 = (float) getvalue("div", var7, 0) / 10.0F;
				}
			}

			var15.close();
		} catch (Exception var16) {
			;
		}

	}

	public ContO(Medium var1, ContO var2, int var3, int var4, int var5) {
		m = var1;
		npl = var2.npl;
		maxR = var2.maxR;
		disp = var2.disp;
		loom = var2.loom;
		colides = var2.colides;
		maxhits = var2.maxhits;
		out = var2.out;
		rcol = var2.rcol;
		pcol = var2.pcol;
		shadow = var2.shadow;
		grounded = var2.grounded;
		p = new Plane[var2.npl];
		x = var3;
		y = var4;
		z = var5;

		for (int var6 = 0; var6 < npl; ++var6) {
			p[var6] = new Plane(m, var2.p[var6].ox, var2.p[var6].oz, var2.p[var6].oy, var2.p[var6].n, var2.p[var6].c);
		}

	}

	public void d(Graphics var1) {
		if (dist != 0) {
			dist = 0;
		}

		int var2 = 0;

		int var3;
		for (var3 = 0; var3 < npl; ++var3) {
			if (!exp) {
				if (p[var3].exp != 0) {
					p[var3].exp = 0;
				}
			} else if (p[var3].exp == 0) {
				p[var3].exp = 1;
			} else if (p[var3].exp == 7) {
				++var2;
			}
		}

		if (!out && var2 != npl) {
			if (fire) {
				dist = 1;
			}

			var3 = m.cx + (int) ((float) (x - m.x - m.cx) * m.cs.getcos(m.xz)
					- (float) (z - m.z - m.cz) * m.cs.getsin(m.xz));
			int var4 = m.cz + (int) ((float) (x - m.x - m.cx) * m.cs.getsin(m.xz)
					+ (float) (z - m.z - m.cz) * m.cs.getcos(m.xz));
			int var5 = m.cz
					+ (int) ((float) (y - m.y - m.cy) * m.cs.getsin(m.zy) + (float) (var4 - m.cz) * m.cs.getcos(m.zy));
			if (xs(var3 + maxR, var5) > 0 && xs(var3 - maxR, var5) < m.w && var5 > -maxR && var5 < '\uc350'
					&& xs(var3 + maxR, var5) - xs(var3 - maxR, var5) > disp || exp) {
				int var6;
				int var8;
				if (shadow || exp) {
					var6 = m.cy + (int) ((float) (m.ground - m.cy) * m.cs.getcos(m.zy)
							- (float) (var4 - m.cz) * m.cs.getsin(m.zy));
					int var7 = m.cz + (int) ((float) (m.ground - m.cy) * m.cs.getsin(m.zy)
							+ (float) (var4 - m.cz) * m.cs.getcos(m.zy));
					if (ys(var6 + maxR, var7) > 0 && ys(var6 - maxR, var7) < m.h || exp) {
						for (var8 = 0; var8 < npl; ++var8) {
							p[var8].s(var1, x - m.x, y - m.y, z - m.z, xz, xy, zy, loom);
						}
					}
				}

				var6 = m.cy + (int) ((float) (y - m.y - m.cy) * m.cs.getcos(m.zy)
						- (float) (var4 - m.cz) * m.cs.getsin(m.zy));
				if (ys(var6 + maxR, var5) > 0 && ys(var6 - maxR, var5) < m.h || exp) {
					if (m.jumping != 0 && m.jumping < 4) {
						hit = true;
					}

					int[] var10 = new int[npl];

					int var9;
					for (var8 = 0; var8 < npl; ++var8) {
						var10[var8] = 0;

						for (var9 = 0; var9 < npl; ++var9) {
							if (p[var8].av != p[var9].av) {
								if (p[var8].av < p[var9].av) {
									++var10[var8];
								}
							} else if (var8 > var9) {
								++var10[var8];
							}
						}
					}

					for (var8 = 0; var8 < npl; ++var8) {
						for (var9 = 0; var9 < npl; ++var9) {
							if (var10[var9] == var8) {
								p[var9].d(var1, x - m.x, y - m.y, z - m.z, xz, xy, zy, hit, wire, loom);
							}
						}
					}

					dist = (int) Math.sqrt((double) ((int) Math.sqrt((double) ((m.x + m.cx - x) * (m.x + m.cx - x)
							+ (m.z - z) * (m.z - z) + (m.y + m.cy - y) * (m.y + m.cy - y))))) * grounded;
				}
			}
		}

		if (hit) {
			hit = false;
			if (m.jumping == 0 && nhits > maxhits) {
				exp = true;
			}
		}

	}

	public void tryexp(ContO var1) {
		if (!var1.exp && !out && !exp) {
			int var2 = getpy(var1.x, var1.y, var1.z);
			if (var2 < maxR / 10 * (maxR / 10) + var1.maxR / 10 * (var1.maxR / 10) && var2 > 0) {
				if (pcol != 0) {
					for (int var3 = 0; var3 < npl; ++var3) {
						for (int var4 = 0; var4 < p[var3].n; ++var4) {
							if ((var1.x - (x + p[var3].ox[var4])) * (var1.x - (x + p[var3].ox[var4]))
									+ (var1.y - (y + p[var3].oy[var4])) * (var1.y - (y + p[var3].oy[var4]))
									+ (var1.z - (z + p[var3].oz[var4])) * (var1.z - (z + p[var3].oz[var4])) < var1.maxR
											* 10 / pcol * (var1.maxR * 10 / pcol)) {
								var1.exp = true;
								break;
							}
						}
					}
				}

				if (rcol != 0 && var2 < maxR / (10 * rcol) * (maxR / (10 * rcol)) + var1.maxR / 10 * (var1.maxR / 10)) {
					var1.exp = true;
				}
			}
		}

	}

	public int getpy(int var1, int var2, int var3) {
		return (var1 - x) / 10 * ((var1 - x) / 10) + (var2 - y) / 10 * ((var2 - y) / 10)
				+ (var3 - z) / 10 * ((var3 - z) / 10);
	}

	public void loadrots(boolean var1) {
		if (!var1) {
			reset();
		}

		for (int var2 = 0; var2 < npl; ++var2) {
			p[var2].rot(p[var2].ox, p[var2].oy, 0, 0, xy, p[var2].n);
			p[var2].rot(p[var2].oy, p[var2].oz, 0, 0, zy, p[var2].n);
			p[var2].rot(p[var2].ox, p[var2].oz, 0, 0, xz, p[var2].n);
			p[var2].loadprojf();
		}

		if (var1) {
			reset();
		}

	}

	public int getvalue(String var1, String var2, int var3) {
		int var5 = 0;
		String var7 = "";

		for (int var4 = var1.length() + 1; var4 < var2.length(); ++var4) {
			String var6 = "" + var2.charAt(var4);
			if (var6.equals(",") || var6.equals(")")) {
				++var5;
				++var4;
			}

			if (var5 == var3) {
				var7 = var7 + var2.charAt(var4);
			}
		}

		return Integer.valueOf(var7).intValue();
	}

	public int xs(int var1, int var2) {
		if (var2 < 10) {
			var2 = 10;
		}

		return (var2 - m.focus_point) * (m.cx - var1) / var2 + var1;
	}
}
