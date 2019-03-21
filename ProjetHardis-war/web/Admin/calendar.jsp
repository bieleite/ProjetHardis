<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="Entites.Disponibilite"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Entites.Client"%>
<%@page import="Entites.Devis"%>
<%@page import="Entites.Utilisateur"%>
<%@page import="Entites.Notification"%>
<%@page import="Entites.Communication"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Hardis Group -¨Profil Administrateur</title>
        <!-- Tell the browser to be responsive to screen width -->
        <%@include  file = "meta.jsp" %>
        <jsp:useBean id="utilisateur" scope="session" class="Utilisateur"></jsp:useBean>
        <jsp:useBean id="listeCommunication" scope="session" class= "java.util.List"></jsp:useBean>
        <jsp:useBean id="listeNotif" scope="session" class = "java.util.List"> </jsp:useBean>
        <jsp:useBean id="listeDevis" scope="session" class = "java.util.List"> </jsp:useBean>
        <jsp:useBean id="listeD" scope="request" class = "java.util.List"> </jsp:useBean>
        </head>
        <body class="hold-transition skin-blue sidebar-mini">
            <div class="wrapper">


            <%@include  file = "header.jsp" %>
            <!-- Left side column. contains the logo and sidebar -->
            <%@include  file = "aside.jsp" %>
            <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/bootstrap/dist/css/bootstrap.min.css">
            <!-- Font Awesome -->
            <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/font-awesome/css/font-awesome.min.css">
            <!-- Ionicons -->
            <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/Ionicons/css/ionicons.min.css">
            <!-- fullCalendar -->
            <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/fullcalendar/dist/fullcalendar.min.css">
            <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/fullcalendar/dist/fullcalendar.print.min.css" media="print">
            <!-- Theme style -->
            <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/dist/css/AdminLTE.min.css">
            <!-- AdminLTE Skins. Choose a skin from the css/skins
                 folder instead of downloading all of them to reduce the load. -->
            <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/dist/css/skins/_all-skins.min.css">

              <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/Ionicons/css/ionicons.min.css">
  <!-- daterange picker -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/plugins/iCheck/all.css">
  <!-- Bootstrap Color Picker -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css">
  <!-- Bootstrap time Picker -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/plugins/timepicker/bootstrap-timepicker.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/bower_components/select2/dist/css/select2.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="http://617981232.r.cdnsun.net/AdminLTE-2.4.10/dist/css/skins/_all-skins.min.css">
  
  
  
  <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Calendar
                        <small>Control panel</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Calendar</li>
                    </ol>
                </section>
                <% List<Disponibilite> listeDis = listeD;%>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-3">
                           
                            <!-- /. box -->
                            <div class="box box-solid">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Create Event</h3>
                                </div>
                                 <div class="box-body">
                <div class="form-group">
                  <label>Libellé</label>
                                    <!-- /btn-group -->
                                    <div class="input-group">
                                        <input id="new-event" type="text"  name="lib" class="form-control" placeholder="Event Title">

                                       
                                    </div>
                </div>
                                 </div>
                                    
                      
            <div class="box-body">
              <!-- Date -->
              <div class="form-group">
                <label>Date</label>

                <div class="input-group date">
                
                  <input type="text"  name="date" class="form-control pull-right" id="datepicker">
                </div>
 </div>
                            </div>
                                 <!-- time Picker -->
             
                                                        
          
            <div class="box-body">
                <div class="form-group">
                  <label>Heure début</label>

                  <div class="input-group">
                    <input type="text" name ="heure" class="form-control timepicker ">

               
                  </div>
                  <!-- /.input group -->
                </div>
                 <div class="form-group">
                  <label>Heure fin</label>

                  <div class="input-group">
                    <input type="text" name ="heureF" class="form-control timepicker ">

               
                  </div>
                  <!-- /.input group -->
                </div>
                <!-- /.form group -->
              </div>   
                                    
                            </div>
                                   
                    <div class="input-group-btn">
                        <a href ="servAdmin?calendar" >  <button id="add-new-event" type="button" class="btn btn-primary btn-flat">Ajouter</button></a>
                                        </div>
                                        <!-- /btn-group -->
                       
                            
                        </div>
     
                        
                        
                        <!-- /.col -->
                        <div class="col-md-9">
                            <div class="box box-primary">
                                <div class="box-body no-padding">
                                    <!-- THE CALENDAR -->
                                    <div id="calendar"></div>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /. box -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

        </div>

        <%@include  file = "scriptCalendar.jsp" %>

        <script>
            $(function () {

                /* initialize the external events
                 -----------------------------------------------------------------*/
                function init_events(ele) {
                    ele.each(function () {

                        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
                        // it doesn't need to have a start or end
                        var eventObject = {
                            title: $.trim($(this).text()) // use the element's text as the event title
                        }

                        // store the Event Object in the DOM element so we can get to it later
                        $(this).data('eventObject', eventObject)

                        // make the event draggable using jQuery UI
                        $(this).draggable({
                            zIndex: 1070,
                            revert: true, // will cause the event to go back to its
                            revertDuration: 0  //  original position after the drag
                        })

                    })
                }


                init_events($('#external-events div.external-event'))

                /* initialize the calendar
                 -----------------------------------------------------------------*/
             
                // document.write(jsVar)

                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    buttonText: {
                        today: 'today',
                        month: 'month',
                        week: 'week',
                        day: 'day'
                    },

                    events: [

            <%for (Disponibilite dispo : listeDis) {
                 Calendar c = Calendar.getInstance();
         
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
         Calendar ca = Calendar.getInstance();
         
        String test =  df.format(dispo.getDateDebut());
       
        
        Timestamp dt = Timestamp.valueOf(test);
        
             c.setTime(dt);
               String dat = dispo.getDateDebut().toGMTString();
               String y =  String.format("%04d", c.get(Calendar.YEAR));
               int mo = c.get(Calendar.MONTH);
               String m =  String.format("%02d", mo+1);
               String d =  String.format("%02d", c.get(Calendar.DAY_OF_MONTH));
               String h =  String.format("%02d", c.get(Calendar.HOUR));
               String min =  String.format("%02d", c.get(Calendar.MINUTE));
       
            
                
                String da = "new Date('2019-"+m+"-"+d+"T"+h+":"+min+":00')";
                
                out.print("{ title  : '"+dispo.getLibelleActivite()+"',start          : "+da+", backgroundColor: '#f56954', borderColor    : '#f56954' },");
                      }

            %>



                    ],
                    editable: true,
                    droppable: true, // this allows things to be dropped onto the calendar !!!
                    drop: function (date, allDay) { // this function is called when something is dropped

                        // retrieve the dropped element's stored Event Object
                        var originalEventObject = $(this).data('eventObject')

                        // we need to copy it, so that multiple events don't have a reference to the same object
                        var copiedEventObject = $.extend({}, originalEventObject)

                        // assign it the date that was reported
                        copiedEventObject.start = date
                        copiedEventObject.allDay = allDay
                        copiedEventObject.backgroundColor = $(this).css('background-color')
                        copiedEventObject.borderColor = $(this).css('border-color')

                        // render the event on the calendar
                        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true)

                        // is the "remove after drop" checkbox checked?
                        if ($('#drop-remove').is(':checked')) {
                            // if so, remove the element from the "Draggable Events" list
                            $(this).remove()
                        }

                    }
                })

                /* ADDING EVENTS */
                var currColor = '#3c8dbc' //Red by default
                //Color chooser button
                var colorChooser = $('#color-chooser-btn')
                $('#color-chooser > li > a').click(function (e) {
                    e.preventDefault()
                    //Save color
                    currColor = $(this).css('color')
                    //Add color effect to button
                    $('#add-new-event').css({'background-color': currColor, 'border-color': currColor})
                })
                $('#add-new-event').click(function (e) {
                    e.preventDefault()
                    //Get value and make sure it is not null
                    var val = $('#new-event').val()
                    if (val.length == 0) {
                        return
                    }

                    //Create events
                    var event = $('<div />')
                    event.css({
                        'background-color': currColor,
                        'border-color': currColor,
                        'color': '#fff'
                    }).addClass('external-event')
                    event.html(val)
                    $('#external-events').prepend(event)

                    //Add draggable funtionality
                    init_events(event)

                    //Remove event from text input
                    $('#new-event').val('')
                })
            })
        </script>
        
        <script>
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()



    //Date picker
    $('#datepicker').datepicker({
      autoclose: true
    })




    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
  })
</script>

    </body>
</html>