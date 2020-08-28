package ru.avokzal63.roadsale.service.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.avokzal63.roadsale.domain.Cheque;
import ru.avokzal63.roadsale.domain.Ticket;
import ru.avokzal63.roadsale.domain.dto.ferma.StatusDataDeviceDto;
import ru.avokzal63.roadsale.repos.dto.TicketRepo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Service
public class TicketService {
    @Autowired
    private TicketRepo ticketRepo;

    public boolean addTicket(Ticket ticket) {
        ticketRepo.save(ticket);
        return true;
    }

    public Ticket getTicketByCheque(Cheque cheque) {
        return ticketRepo.findByChequeIs(cheque);
    }

    public Ticket getTicketById(String id) {
        return ticketRepo.findOneById(id);
    }

    public String getLinkById(String id) {
        Ticket ticketById = getTicketById(id);
//        https://check.ofd.ru/rec/9282440300498101/0000000032/3232067547?format=html
        StatusDataDeviceDto statusDataDeviceDto = ticketById.getCheque().getStatusDataDto().getStatusDataDeviceDto();
        String link = new StringBuilder()
                .append("https://check.ofd.ru/rec/")
                .append(statusDataDeviceDto.getFn()).append("/")
                .append(statusDataDeviceDto.getFdn()).append("/")
                .append(statusDataDeviceDto.getFpd()).append("?format=html")
                .toString();
        return link;
    }

    public String getQrFromId(String id) {
        Ticket ticketById = getTicketById(id);
        StatusDataDeviceDto statusDataDeviceDto = ticketById.getCheque().getStatusDataDto().getStatusDataDeviceDto();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        format.setTimeZone(TimeZone.getTimeZone( "UTC" ));
        String date = format.format(ticketById.getCheque().getStatusDataDto().getModifiedDateUtc());
        return new StringBuilder()
                .append("t=")
                .append(date)
                .append("&s=")
                .append(ticketById.getPrice())
                .append("&fn=")
                .append(ticketById.getCheque().getStatusDataDto().getStatusDataDeviceDto().getFn())
                .append("&i=")
                .append(ticketById.getCheque().getStatusDataDto().getStatusDataDeviceDto().getFdn().replaceAll("^0+", ""))
                .append("&fp=")
                .append(ticketById.getCheque().getStatusDataDto().getStatusDataDeviceDto().getFpd())
                .append("&n=1").toString();
    }

    public List<Ticket> findAll() {
        return ticketRepo.findAll();
    }

}
