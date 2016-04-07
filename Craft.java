import java.awt.Graphics;

public class Craft {

	cControl u = new cControl();
	int rspeed = 0;
	float speed = 0.0F;
	int rlift = 0;
	double lift = 0.0D;
	boolean pexp = false;
	int ltyp = 3;
	int[] lx = new int[20];
	int[] ly = new int[20];
	int[] lz = new int[20];
	int[] lxz = new int[20];
	int[] lzy = new int[20];
	int[] lxy = new int[20];
	int[] lstage = new int[20];
	int[] lspeed = new int[20];
	int[] lhit = new int[20];
	int[] nf = new int[20];
	int nl = 0;
	Lasers lsr;
	boolean skip = false;
	int bulkc = 0;
	int[] sms = new int[4];
	int[] sx = new int[4];
	int[] sy = new int[4];
	int[] sz = new int[4];
	int[] sxz = new int[4];
	int[] szy = new int[4];
	int ns = 0;
	boolean smoke = false;
	int[] dms = new int[4];
	int[] dx = new int[4];
	int[] dy = new int[4];
	int[] dz = new int[4];
	int[] dxz = new int[4];
	int[] dzy = new int[4];
	int nd = 0;
	int gxz = 0;
	int gzy = 0;
	boolean responce = false;
	int trgxz = 0;
	int trgzy = 0;
	int out = 0;
	int turnat = (int) (Math.random() * 50.0D);
	int tcnt = 0;
	boolean engage = true;
	int enx = 0;
	int eny = 0;
	int enz = 0;
	int ens = 4;
	boolean targeting = false;
	int mode = 0;
	int m3o = 0;
	int m3cnt = 0;
	int m1cnt = 0;
	int relax = 50;
	int runn = 30;
	int liftup = 500;
	boolean dracs = false;

	public void preform(ContO var1, ContO[] var2, int[] var3, int var4, int var5, int var6) {
		int var7;
		for (var7 = Math.abs(var1.zy); var7 > 360; var7 -= 360) {
			;
		}

		byte var8 = 1;
		if (var7 > 90 && var7 < 270) {
			var8 = -1;
		}

		int var9;
		int var10;
		int var11;
		int var12;
		int var15;
		int var16;
		if (var1.y < 207) {
			if (u.up) {
				var1.zy -= (int) (5.0F * var1.m.cs.getcos(var1.xy));
				var1.xz += (int) ((float) (var8 * 3) * var1.m.cs.getsin(var1.xy));
			}

			if (u.down) {
				var1.zy += (int) (5.0F * var1.m.cs.getcos(var1.xy));
				var1.xz -= (int) ((float) (var8 * 3) * var1.m.cs.getsin(var1.xy));
			}
		} else {
			for (var9 = Math.abs(var1.zy); var9 > 90; var9 -= 180) {
				;
			}

			for (var10 = Math.abs(var1.xy); var10 > 90; var10 -= 180) {
				;
			}

			for (var11 = Math.abs(var1.zy); var11 > 270; var11 -= 360) {
				;
			}

			for (var12 = Math.abs(var1.xy); var12 > 270; var12 -= 360) {
				;
			}

			boolean var13 = Math.abs(var11) < 90 && Math.abs(var12) < 90
					|| Math.abs(var11) > 90 && Math.abs(var12) > 90;
			boolean var14 = Math.abs(var9) > 30 || Math.abs(var10) > 30;
			if ((!var13 || var14) && !var1.exp) {
				var1.exp = true;
				var1.y = 170;
				speed = 30.0F;
				pexp = true;
			}

			for (var15 = Math.abs(var1.zy); var15 > 270; var15 -= 360) {
				;
			}

			if (var15 > 90) {
				var1.xy = 180;
			} else {
				var1.xy = 0;
			}

			for (var16 = var1.zy; var16 > 90; var16 -= 180) {
				;
			}

			while (var16 < -90) {
				var16 += 180;
			}

			if (var16 > 0) {
				var1.zy += -1;
				smoke = true;
			}

			if (var16 < 0) {
				++var1.zy;
				smoke = true;
			}

			if (speed > 10.0F && u.down) {
				var1.zy += (int) (5.0F * var1.m.cs.getcos(var1.xy));
			}
		}

		if (u.left) {
			if (var1.y < 207) {
				if (var1.xy > -90) {
					var1.xy -= 10;
				}
			} else {
				var1.xz += 2;
			}
		}

		if (u.right) {
			if (var1.y < 207) {
				if (var1.xy < 90) {
					var1.xy += 10;
				}
			} else {
				var1.xz -= 2;
			}
		}

		var9 = (int) ((float) (var8 * 4) * var1.m.cs.getsin(var1.xy));
		var1.xz -= var9;
		if (var1.nhits > var1.maxhits - var1.maxhits / 6 && !var1.exp) {
			if (rspeed > 60) {
				rspeed = 60;
				speed = 60.0F;
			}

			var1.xz += (int) (Math.random() * (double) (speed / 10.0F) - (double) (speed / 20.0F));
			var1.zy += (int) (Math.random() * (double) (speed / 10.0F) - (double) (speed / 20.0F));
		}

		rlift = (int) (speed * var1.m.cs.getcos(var1.zy) * var1.m.cs.getcos(var1.xy)) - 40;
		if (lift < (double) rlift) {
			lift += 0.5D;
		}

		if (lift > (double) rlift) {
			lift -= 0.5D;
		}

		if (lift < (double) (-(50.0F - speed / 2.0F))) {
			lift = (double) (-(50.0F - speed / 2.0F));
		}

		var10 = (int) (5.0F * var1.m.cs.getcos(var1.zy) * var1.m.cs.getcos(var1.xy));
		if (lift > (double) var10) {
			lift = (double) var10;
		}

		var1.y -= (int) lift;
		if (var1.x < -40000) {
			var1.x = -40000;
			if (var9 <= 0) {
				var1.xz += 5;
			} else {
				var1.xz -= 5;
			}
		}

		if (var1.x > '\u9c40') {
			var1.x = '\u9c40';
			if (var9 <= 0) {
				var1.xz += 5;
			} else {
				var1.xz -= 5;
			}
		}

		if (var1.z > '\u9c40') {
			var1.z = '\u9c40';
			if (var9 <= 0) {
				var1.xz += 5;
			} else {
				var1.xz -= 5;
			}
		}

		if (var1.z < -40000) {
			var1.z = -40000;
			if (var9 <= 0) {
				var1.xz += 5;
			} else {
				var1.xz -= 5;
			}
		}

		if (!pexp && var1.exp) {
			if (speed > 30.0F) {
				speed = -15.0F;
				pexp = true;
			} else if (var1.nhits > var1.maxhits) {
				pexp = true;
			} else {
				var1.exp = false;
				speed = -(((float) rspeed + speed) / 2.0F);
			}
		}

		if (pexp) {
			if (speed > 0.0F) {
				speed = (float) ((double) speed - 0.3D);
			}

			if (speed < 0.0F) {
				speed = (float) ((double) speed + 0.3D);
			}
		} else {
			if (speed > (float) rspeed) {
				speed = (float) ((double) speed - 0.5D);
			}

			if (speed < (float) rspeed) {
				++speed;
			}
		}

		if (u.fire && !var1.exp) {
			if (skip && bulkc < lsr.srate[ltyp]) {
				lx[nl] = var1.x;
				ly[nl] = var1.y;
				lz[nl] = var1.z;
				lxz[nl] = var1.xz;
				lzy[nl] = var1.zy;
				lxy[nl] = var1.xy;
				if (ly[nl] > 215) {
					ly[nl] = 215;
				}

				lspeed[nl] = (int) ((float) lsr.speed[ltyp] + speed);
				lstage[nl] = 1;
				lhit[nl] = 0;
				nf[nl] = 0;
				++nl;
				if (nl == 20) {
					nl = 0;
				}

				skip = false;
			} else if (!skip) {
				skip = true;
			}

			++bulkc;
			if (bulkc > 12) {
				bulkc = 0;
			}
		}

		var11 = 0;
		var12 = 0;

		int var17;
		int var20;
		int var21;
		do {
			if (lstage[var12] != 0) {
				++var11;
				if (ly[var12] > 240 && lhit[var12] == 0) {
					lhit[var12] = 1;
				}

				if (lhit[var12] == 0) {
					if (lstage[var12] > 10 && nf[var12] < 15) {
						var20 = -1;
						var21 = -1;
						if (!var2[var5].exp) {
							var20 = getpy(var2[var5].x, var2[var5].y, var2[var5].z, var12);
							var21 = var5;
						}

						for (var15 = var6; var15 < var6 + 13; ++var15) {
							var16 = getpy(var2[var15].x, var2[var15].y, var2[var15].z, var12);
							if (var16 < var20 && var16 > 0 && !var2[var15].exp) {
								var20 = var16;
								var21 = var15;
							}
						}

						if (var20 < 22500 && var20 > 0) {
							if (lspeed[var12] > 230) {
								lspeed[var12] = 230;
							}

							var15 = var2[var21].x;
							var16 = var2[var21].z;
							var17 = var2[var21].y;
							short var18 = 0;
							if (var15 - lx[var12] > 0) {
								var18 = 180;
							}

							lxz[var12] = (int) ((double) (90 + var18)
									+ Math.atan((double) (var16 - lz[var12]) / (double) (var15 - lx[var12]))
											/ 0.017453292519943295D);
							short var25 = 0;
							if (var17 - ly[var12] < 0) {
								var25 = -180;
							}

							int var19 = (int) Math.sqrt((double) ((var16 - lz[var12]) * (var16 - lz[var12])
									+ (var15 - lx[var12]) * (var15 - lx[var12])));
							lzy[var12] = -((int) ((double) (90 + var25)
									- Math.atan((double) var19 / (double) (var17 - ly[var12]))
											/ 0.017453292519943295D));
							++nf[var12];
						}
					}

					lx[var12] -= (int) ((float) lspeed[var12] * var1.m.cs.getsin(lxz[var12])
							* var1.m.cs.getcos(lzy[var12]));
					lz[var12] += (int) ((float) lspeed[var12] * var1.m.cs.getcos(lxz[var12])
							* var1.m.cs.getcos(lzy[var12]));
					ly[var12] -= (int) ((float) lspeed[var12] * var1.m.cs.getsin(lzy[var12]));
					++lstage[var12];
					if (lstage[var12] > 80) {
						lstage[var12] = 0;
					}
				}
			}

			++var12;
		} while (var12 < 20);

		if (var11 != 0) {
			if (!var1.fire) {
				var1.fire = true;
			}
		} else if (var1.fire) {
			var1.fire = false;
		}

		var1.x -= (int) (speed * var1.m.cs.getsin(var1.xz) * var1.m.cs.getcos(var1.zy));
		var1.z += (int) (speed * var1.m.cs.getcos(var1.xz) * var1.m.cs.getcos(var1.zy));
		var1.y -= (int) (speed * var1.m.cs.getsin(var1.zy));
		if (var1.y > 215) {
			var1.y = 215;
		}

		if (var1.y < -25000) {
			var1.y = -25000;
		}

		if (tcnt > turnat) {
			if (targeting) {
				targeting = false;
			}

			short var23;
			short var24;
			if (mode != 1 && mode != 3) {
				if (engage) {
					var23 = 0;
					if (var2[var6 + ens].x - var1.x > 0) {
						var23 = 180;
					}

					gxz = (int) ((double) (90 + var23)
							+ Math.atan((double) (var2[var6 + ens].z - var1.z) / (double) (var2[var6 + ens].x - var1.x))
									/ 0.017453292519943295D);
					var24 = 0;
					if (var2[var6 + ens].y - var1.y < 0) {
						var24 = -180;
					}

					var21 = (int) Math.sqrt((double) ((var2[var6 + ens].z - var1.z) * (var2[var6 + ens].z - var1.z)
							+ (var2[var6 + ens].x - var1.x) * (var2[var6 + ens].x - var1.x)));
					gzy = -((int) ((double) (90 + var24)
							- Math.atan((double) var21 / (double) (var2[var6 + ens].y - var1.y))
									/ 0.017453292519943295D));
					var12 = getcpy(var1, var2[var6 + ens]);
					if (var12 > 0 && var12 < 15000) {
						targeting = true;
					}

					if (var12 > 0 && var12 < 200 && Math.random() > 0.7D) {
						if (Math.random() > 0.5D) {
							enx = -6800 + (int) (2000.0D + 30000.0D * Math.random());
						} else {
							enx = -6800 - (int) (2000.0D + 30000.0D * Math.random());
						}

						if (Math.random() > 0.5D) {
							enz = -1150 + (int) (2000.0D + 30000.0D * Math.random());
						} else {
							enz = -1150 - (int) (2000.0D + 30000.0D * Math.random());
						}

						if (Math.random() > 0.7D) {
							eny = 0;
						} else {
							eny = -((int) (Math.random() * 23000.0D));
						}

						engage = false;
						targeting = false;
					}
				} else {
					var23 = 0;
					if (enx - var1.x > 0) {
						var23 = 180;
					}

					gxz = (int) ((double) (90 + var23)
							+ Math.atan((double) (enz - var1.z) / (double) (enx - var1.x)) / 0.017453292519943295D);
					var24 = 0;
					if (eny - var1.y < 0) {
						var24 = -180;
					}

					var21 = (int) Math
							.sqrt((double) ((enz - var1.z) * (enz - var1.z) + (enx - var1.x) * (enx - var1.x)));
					gzy = -((int) ((double) (90 + var24)
							- Math.atan((double) var21 / (double) (eny - var1.y)) / 0.017453292519943295D));
					var12 = getepy(var1);
					if (var12 > 0 && var12 < 500) {
						ens = 4 + (int) (Math.random() * 5.0D);
						engage = true;
					}
				}

				turnat = (int) (Math.random() * 50.0D);
			}

			var12 = getcpy(var2[var5], var1);
			if (var12 > 0) {
				if (var12 < 20000 && !var2[var5].exp) {
					if (mode == 0 && mode != 3) {
						if (Math.random() > 0.5D && var1.maxR != 151) {
							mode = 2;
						} else {
							mode = 1;
							m1cnt = 0;
						}
					}
				} else if (mode != 0) {
					mode = 0;
				}
			}

			if (mode == 1) {
				var23 = 0;
				if (var2[var5].x - var1.x > 0) {
					var23 = 180;
				}

				gxz = (int) ((double) (90 + var23)
						+ Math.atan((double) (var2[var5].z - var1.z) / (double) (var2[var5].x - var1.x))
								/ 0.017453292519943295D);
				var24 = 0;
				if (var2[var5].y - var1.y < 0) {
					var24 = -180;
				}

				var21 = (int) Math.sqrt((double) ((var2[var5].z - var1.z) * (var2[var5].z - var1.z)
						+ (var2[var5].x - var1.x) * (var2[var5].x - var1.x)));
				gzy = -((int) ((double) (90 + var24)
						- Math.atan((double) var21 / (double) (var2[var5].y - var1.y)) / 0.017453292519943295D));
				turnat = (int) (Math.random() * 3.0D);
				if (var12 < 7000) {
					targeting = true;
				}

				++m1cnt;
				if (m1cnt > relax) {
					mode = 0;
				}
			}

			if (mode == 3) {
				var23 = 0;
				if (var2[m3o].x - var1.x > 0) {
					var23 = 180;
				}

				gxz = (int) ((double) (90 + var23)
						+ Math.atan((double) (var2[m3o].z - var1.z) / (double) (var2[m3o].x - var1.x))
								/ 0.017453292519943295D);
				var24 = 0;
				if (var2[m3o].y - var1.y < 0) {
					var24 = -180;
				}

				var21 = (int) Math.sqrt((double) ((var2[m3o].z - var1.z) * (var2[m3o].z - var1.z)
						+ (var2[m3o].x - var1.x) * (var2[m3o].x - var1.x)));
				gzy = -((int) ((double) (90 + var24)
						- Math.atan((double) var21 / (double) (var2[m3o].y - var1.y)) / 0.017453292519943295D));
				turnat = (int) (Math.random() * 10.0D);
				++m3cnt;
				if (m3cnt == runn) {
					mode = 0;
				}
			}

			tcnt = 0;
		} else {
			++tcnt;
		}

		if (mode != 3 && var1.hit && Math.random() > 0.85D) {
			if (Math.random() > 0.5D) {
				m3o = nearst(var2, var3, var4, var5, var1);
				mode = 3;
				m3cnt = 0;
			} else if (mode == 2) {
				if (var1.zy < 15 && Math.random() < 0.5D && var1.maxR != 151) {
					turnat = 20;
					gzy = 80;
					mode = 0;
				} else {
					mode = 1;
					m1cnt = 0;
				}
			} else if (var1.zy < 15 && Math.random() < 0.5D) {
				turnat = 20;
				gzy = 80;
				mode = 0;
			} else {
				mode = 2;
			}
		}

		byte var22 = 0;
		if ((float) var1.y > 100.0F + (float) liftup * var1.m.cs.getsin(var1.zy)) {
			var22 = 1;
		}

		var20 = var1.y + (int) ((float) (-(var1.z + 1000 - var1.z)) * var1.m.cs.getsin(var1.zy));
		var21 = var1.z + (int) ((float) (var1.z + 1000 - var1.z) * var1.m.cs.getcos(var1.zy));
		var15 = var1.x + (int) ((float) (-(var21 - var1.z)) * var1.m.cs.getsin(var1.xz));
		var16 = var1.z + (int) ((float) (var21 - var1.z) * var1.m.cs.getcos(var1.xz));
		if (myway(var2, var3, var4, var5, var15, var20, var16)) {
			var22 = 2;
		}

		if (u.left) {
			u.left = false;
		}

		if (u.right) {
			u.right = false;
		}

		if (u.up) {
			u.up = false;
		}

		if (u.down) {
			u.down = false;
		}

		if (var22 != 2) {
			for (var17 = var1.xz; var17 >= 360; var17 -= 360) {
				;
			}

			while (var17 < 0) {
				var17 += 360;
			}

			if (Math.abs(var17 - gxz) > 5 && var22 == 0) {
				if (var17 > 270 && gxz < 90) {
					u.left = true;
					trgxz = 360 - var17 + gxz;
				} else if (var17 < 90 && gxz > 270) {
					u.right = true;
					trgxz = 360 - gxz + var17;
				} else if (var17 < gxz) {
					u.left = true;
					trgxz = gxz - var17;
				} else {
					u.right = true;
					trgxz = var17 - gxz;
				}

				if (dracs && Math.abs(var1.xy) > 80) {
					u.down = true;
				}
			} else {
				if (var1.xy > 0) {
					u.left = true;
				}

				if (var1.xy < 0) {
					u.right = true;
				}

				if (var22 == 1 && Math.abs(var1.xy) < 30 && var1.zy < -30) {
					gzy = 20;
				}
			}

			if (Math.abs(var1.zy - gzy) > 5 && Math.abs(var1.xy) < 45) {
				if (gzy < var1.zy) {
					u.up = true;
				}

				if (gzy > var1.zy) {
					u.down = true;
				}

				trgzy = Math.abs(var1.zy - gzy);
			}
		} else if (Math.abs(var1.xy) >= 60 && var1.zy >= 10) {
			if (var1.xy > 0) {
				u.right = true;
			}

			if (var1.xy < 0) {
				u.left = true;
			}

			if (var1.zy < 80) {
				u.down = true;
			}

			tcnt = 0;
			turnat = (int) (Math.random() * 4.0D + 3.0D);
		} else {
			if (var1.xy > 0) {
				u.left = true;
			}

			if (var1.xy < 0) {
				u.right = true;
			}

			if (var1.zy < 80) {
				u.down = true;
				gzy = 80;
			}

			tcnt = 0;
			turnat = (int) (Math.random() * 6.0D + 4.0D);
		}

		if (trgxz < 90 && trgzy < 40 && targeting) {
			if (!u.fire) {
				u.fire = true;
			}
		} else if (u.fire) {
			u.fire = false;
		}

	}

	public void dosmokes(Graphics var1, ContO var2) {
		if (!var2.exp) {
			int var3;
			if (var2.nhits > var2.maxhits - var2.maxhits / 3) {
				if (dms[nd] == -1) {
					dx[nd] = var2.x + (int) (Math.random() * 60.0D - 30.0D);
					dy[nd] = var2.y;
					dz[nd] = var2.z;
					dxz[nd] = var2.xz;
					dzy[nd] = var2.zy;
					dms[nd] = 0;
					++nd;
					if (nd == 4) {
						nd = 0;
					}
				}

				var3 = 0;

				do {
					if (dms[var3] != -1) {
						if (dms[var3] < 4) {
							lsr.hsmoke(var1, dx[var3], dy[var3], dz[var3], dxz[var3], dzy[var3], dms[var3]);
						}

						dy[var3] -= 15;
						++dms[var3];
						if (dms[var3] >= 7) {
							dms[var3] = -1;
						}
					}

					++var3;
				} while (var3 < 4);
			}

			if (var2.y > 200) {
				if (smoke && sms[ns] == -1) {
					sx[ns] = var2.x + (int) (Math.random() * 80.0D - 40.0D);
					sy[ns] = var2.y + 15;
					sz[ns] = var2.z;
					sxz[ns] = var2.xz;
					szy[ns] = var2.zy;
					sms[ns] = 0;
					++ns;
					if (ns == 4) {
						ns = 0;
					}

					smoke = false;
				}

				var3 = 0;

				do {
					if (sms[var3] != -1) {
						if (sms[var3] < 4) {
							lsr.gsmoke(var1, sx[var3], sy[var3], sz[var3], sxz[var3], szy[var3], sms[var3]);
						}

						sy[var3] -= 15;
						++sms[var3];
						if (sms[var3] == 10) {
							sms[var3] = -1;
						}
					}

					++var3;
				} while (var3 < 4);
			}
		}

	}

	public int nearst(ContO[] var1, int[] var2, int var3, int var4, ContO var5) {
		int var6 = getcpy(var1[var2[0]], var5);
		int var7 = var2[0];

		for (int var8 = 0; var8 < var3; ++var8) {
			if (var2[var8] != var4) {
				int var9 = getcpy(var1[var2[var8]], var5);
				if (var9 > 0 && var9 < var6 && !var1[var2[var8]].exp || var6 < 0) {
					var6 = var9;
					var7 = var2[var8];
				}
			}
		}

		return var7;
	}

	public void reset(int var1, int var2, int var3, int var4, int var5, int var6) {
		rspeed = var1;
		speed = (float) var1;
		rlift = 0;
		lift = 0.0D;
		pexp = false;
		ltyp = var2;
		mode = 0;
		relax = var3;
		runn = var4;
		liftup = var5;
		if (var6 == 1) {
			dracs = true;
		} else {
			dracs = false;
		}

		int var7 = 0;

		do {
			lstage[var7] = 0;
			++var7;
		} while (var7 < 20);

	}

	public Craft(Medium m) {
		lsr = new Lasers(m);

		for (int i = 0; i < 4; i++) {
			sms[i] = -1;
			dms[i] = -1;
		}
	}

	public void lasercolid(ContO var1) {
		if (!var1.exp && !var1.out) {
			int var2 = 0;

			do {
				if (lstage[var2] != 0 && lhit[var2] == 0) {
					int var3 = getpy(var1.x, var1.y, var1.z, var2);
					if (var3 < var1.maxR / 10 * (var1.maxR / 10) && var3 > 0) {
						if (var1.rcol != 0 && var3 < var1.maxR / (10 * var1.rcol) * (var1.maxR / (10 * var1.rcol))
								+ lsr.rads[ltyp] / 10 * (lsr.rads[ltyp] / 10)) {
							lhit[var2] = 1;
							if (var1.maxhits != -1) {
								var1.hit = true;
								var1.nhits += lsr.damg[ltyp];
							}
						}

						if (var1.pcol != 0) {
							for (int var4 = 0; var4 < var1.npl; ++var4) {
								for (int var5 = 0; var5 < var1.p[var4].n; ++var5) {
									if (!var1.hit && (lx[var2] - (var1.x + var1.p[var4].ox[var5]))
											* (lx[var2] - (var1.x + var1.p[var4].ox[var5]))
											+ (ly[var2] - (var1.y + var1.p[var4].oy[var5]))
													* (ly[var2] - (var1.y + var1.p[var4].oy[var5]))
											+ (lz[var2] - (var1.z + var1.p[var4].oz[var5]))
													* (lz[var2] - (var1.z + var1.p[var4].oz[var5])) < lsr.rads[ltyp]
															* 10 / var1.pcol * (lsr.rads[ltyp] * 10 / var1.pcol)) {
										lhit[var2] = 1;
										if (var1.maxhits != -1) {
											var1.hit = true;
											var1.nhits += lsr.damg[ltyp];
										}
									}
								}
							}
						}
					}
				}

				++var2;
			} while (var2 < 20);
		}

	}

	public int getpy(int var1, int var2, int var3, int var4) {
		return (var1 - lx[var4]) / 10 * ((var1 - lx[var4]) / 10) + (var2 - ly[var4]) / 10 * ((var2 - ly[var4]) / 10)
				+ (var3 - lz[var4]) / 10 * ((var3 - lz[var4]) / 10);
	}

	public int getcpy(ContO var1, ContO var2) {
		return (var1.x - var2.x) / 100 * ((var1.x - var2.x) / 100) + (var1.y - var2.y) / 100 * ((var1.y - var2.y) / 100)
				+ (var1.z - var2.z) / 100 * ((var1.z - var2.z) / 100);
	}

	public boolean myway(ContO[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7) {

		for (int var9 = 0; var9 < var3; ++var9) {
			if (var2[var9] != var4) {
				int var10 = var1[var2[var9]].maxR / 20 * (var1[var2[var9]].maxR / 20);
				if (var10 < 5000) {
					var10 = 5000;
				}

				int var11 = (var1[var2[var9]].x - var5) / 10 * ((var1[var2[var9]].x - var5) / 10)
						+ (var1[var2[var9]].y - var6) / 10 * ((var1[var2[var9]].y - var6) / 10)
						+ (var1[var2[var9]].z - var7) / 10 * ((var1[var2[var9]].z - var7) / 10);
				if (var11 > 0 && var11 < var10 && !var1[var2[var9]].exp && var1[var2[var9]].maxR > 75) {
					return true;
				}
			}
		}

		return false;
	}

	public void dl(Graphics var1) {
		int var2 = 0;

		do {
			if (lstage[var2] != 0) {
				lsr.d(var1, ltyp, lx[var2], ly[var2], lz[var2], lxz[var2], lzy[var2], lxy[var2], lhit[var2]);
				if (lhit[var2] != 0) {
					++lhit[var2];
					if (lhit[var2] > 2) {
						lstage[var2] = 0;
					}
				}
			}

			++var2;
		} while (var2 < 20);

	}

	public int getepy(ContO var1) {
		return (var1.x - enx) / 100 * ((var1.x - enx) / 100) + (var1.y - eny) / 100 * ((var1.y - eny) / 100)
				+ (var1.z - enz) / 100 * ((var1.z - enz) / 100);
	}
}
