ObjectArrayList<Future<ObjectArrayList<ObjectArrayList<String>>>> result =
                                                  new ObjectArrayList<>();

ObjectArrayList<ObjectArrayList<String>> insiemiC = new ObjectArrayList<>();

  	for(int i=0;i<colNumber;i++){
  		visitaVettore(vettoreColonna,i,map,debugCode);
  		 result.add(exec.submit(new FeasabilityTask(i, colNumber, map,
                                  vettoreColonna,thresholds[i])));
  	}

			ObjectArrayList<Future<ObjectArrayList<RFDMap>>> resultMinmality =
                                                    new ObjectArrayList<>();
			for(int i=0;i<colNumber;i++){

				insiemiC = result.get(i).get();

				for(int k=0; k<insiemiC.size(); k++) {
					ObjectArrayList<String> insieme = insiemiC.get(k);
					for(int k2=0; k2<insieme.size(); k2++)
						System.out.println(insieme.get(k2));
				}
				resultMinmality.add(exec.submit(new MinimalityTask(insiemiC, colNumber,
                                          i, colNames, thresholds, debugCode)));
			}

			for(int i=0;i<colNumber;i++){
				listaCC.addRFDS(resultMinmality.get(i).get());
			}
