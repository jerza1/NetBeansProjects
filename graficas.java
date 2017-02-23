/*       
        ChartPanel panel;
        JFreeChart chart = null;
        
        if(l.isSelected()){
            int validar = 1;
            XYSplineRenderer render = new XYSplineRenderer();
            XYSeriesCollection dataset = new XYSeriesCollection();
            
            ValueAxis x = new NumberAxis();
            ValueAxis y = new NumberAxis();
            
            XYSeries serie = new XYSeries("Datos");
            XYPlot plot;
            gLineas.removeAll();
            
            try{
                for(int fila = 0; fila < 6; fila++){
                    serie.add(Float.parseFloat(String.valueOf(datos.getValueAt(fila, 0))),
                            Float.parseFloat(String.valueOf(datos.getValueAt(fila, 1))));
                }
            }catch(Exception ex){
                validar = 0;
            }
            if(validar == 1){
                dataset.addSeries(serie);
                
                x.setLabel("Ejer X");
                y.setLabel("Eje Y");
                
                plot = new XYPlot(dataset,x,y,render);
                chart = new JFreeChart(plot);
                chart.setTitle("Graficos de Lineas");
            }else{JOptionPane.showMessageDialog(this, "Debe llenar la tabla con datos numericos");
            }
        }else{
            
        if(b.isSelected()){
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            String producto1 = "Sopas";
            String producto2 = "Soda";
            
            String dia1 = "Dis 1";
            String dia2 = "Dis 2";
            String dia3 = "Dis 3";
            String dia4 = "Dis 4";
            
            data.addValue(18, producto1, dia1);
            data.addValue(15, producto1, dia2);
            data.addValue(14, producto1, dia3);
            data.addValue(1, producto1, dia4);
            
            data.addValue(50, producto2, dia1);
            data.addValue(45, producto2, dia2);
            data.addValue(30, producto2, dia3);
            data.addValue(10, producto2, dia4);
            
            chart = ChartFactory.createBarChart("Grafico de Barras", "Dias",
                    "Cantidad", data, PlotOrientation.HORIZONTAL, true, true, true);
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            plot.setDomainGridlinesVisible(true);
            try {
            ChartUtilities.saveChartAsJPEG(new File("ImagenGuardada.jpg"), chart, 500, 300);
          new File("ImagenGuardada.jpg");
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(this, "Se ha producido un error al intentar guardar","Error",JOptionPane.ERROR_MESSAGE);
        }
        }
        else{
            DefaultPieDataset data = new DefaultPieDataset();
            data.setValue("Categoria 1", 20);
            data.setValue("Categoria 2", 60);
            data.setValue("Categoria 3", 20);
            
            chart = ChartFactory.createPieChart3D("Grafico de Paste", data, true, true, true);
            
        }
       }
        panel = new ChartPanel(chart);
        panel.setBounds(5, 10, 350, 350);
        
        if(l.isSelected()){
            gLineas.add(panel);
            gLineas.repaint();
        }else{
            if(b.isSelected()){
                gBarras.add(panel);
            gBarras.repaint();
                
            }else{
                gPastel.add(panel);
            gPastel.repaint();
            }
        }*/


try {
            ChartUtilities.saveChartAsJPEG(new File("ImagenGuardada.jpg"), chart, 1024, 500);
          new File("ImagenGuardada.jpg");
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(null, "Se ha producido un error al intentar guardar","Error",JOptionPane.ERROR_MESSAGE);
        }