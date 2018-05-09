package br.com.caelum.financas.service;

import javax.annotation.Resource;
import javax.ejb.*;

@Singleton
@Startup
public class Agendador {

	private static int totalCriado;

	@Resource
	private TimerService timerService;

	public void executa() {
		System.out.printf("%d instancias criadas %n", totalCriado);

		// simulando demora de 4s na execucao
		try {
			System.out.printf("Executando %s %n", this);
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}

	public void agenda(String expressaoMinutos, String expressaoSegundos) {

        ScheduleExpression expression = new ScheduleExpression();
        expression.hour("*");
        expression.minute(expressaoMinutos);
        expression.second(expressaoSegundos);

        TimerConfig config = new TimerConfig();
        config.setInfo(expression.toString());
        config.setPersistent(false);

        timerService.createCalendarTimer(expression, config);

        System.out.println("Agendamento: " + expression);
    }

    @Timeout
    public void verificacaoPeriodicaSeHaNovasContas(Timer timer) {
	    System.out.println(timer.getInfo());
    }
}
