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
                                
                                <form role="form">
              <div class="box-body">
                         <div class="form-group">
                                            <label>Libellé</label>
                                            <!-- /btn-group -->
                                            <div class="input-group">
                                                <input id="new-event" type="text"  name="lib" class="form-control" placeholder="Event Title">


                                            </div>
                                        </div>
                  <div class="row">
                      <div class="col-md-6">
                
                          
                          <div class="form-group">
                <label>Date début</label>

                <div class="input-group date">
                 
                  <input type="text" class="form-control pull-right" id="datepicker" name="dd">
                </div>
                <!-- /.input group -->
              </div>      
                      </div>
                      <div class="col-md-6">
                        <div class="form-group">
                  <label>Heure début</label>

                  <div class="input-group">
                    <input type="text" class="form-control timepicker" name="hd">

                   
                  </div>
                  <!-- /.input group -->
                </div>
                      </div> </div>
                       <div class="row">
                      <div class="col-md-6">
                  <div class="form-group">
                <label>Date fin </label>

                <div class="input-group date">
                 
                  <input type="text" class="form-control pull-right" id="datepicker1" name="df">
                </div>
                <!-- /.input group -->
              </div>      
                      </div>
                      <div class="col-md-6">
                        <div class="form-group">
                  <label>Heure fin </label>

                  <div class="input-group">
                    <input type="text" class="form-control timepicker" name="hf">

                   
                  </div>
                  <!-- /.input group -->
                </div>
                      </div> </div>


              
                
                   <input type ="hidden" name="action" value="calendar">
   
              </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Valider</button>
              </div>
            </form>
                  

     </div>
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
                    Calendar c1 = Calendar.getInstance();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

                    String test = df.format(dispo.getDateDebut());
                    String test1 = df.format(dispo.getDateFin());

                    Timestamp dt = Timestamp.valueOf(test);
                    Timestamp dt1 = Timestamp.valueOf(test1);
                    c.setTime(dt);

                    String y = String.format("%04d", c.get(Calendar.YEAR));
                    int mo = c.get(Calendar.MONTH);
                    String m = String.format("%02d", mo + 1);
                    String d = String.format("%02d", c.get(Calendar.DAY_OF_MONTH));
                    String h = String.format("%02d", c.get(Calendar.HOUR_OF_DAY));
                    String min = String.format("%02d", c.get(Calendar.MINUTE));

                    String daD = "new Date('2019-" + m + "-" + d + "T" + h + ":" + min + ":00')";

                    c1.setTime(dt1);

                    y = String.format("%04d", c1.get(Calendar.YEAR));
                    mo = c1.get(Calendar.MONTH);
                    m = String.format("%02d", mo + 1);
                    d = String.format("%02d", c1.get(Calendar.DAY_OF_MONTH));
                    h = String.format("%02d", c1.get(Calendar.HOUR_OF_DAY));
                    min = String.format("%02d", c1.get(Calendar.MINUTE));
                    String daFi = "new Date('2019-" + m + "-" + d + "T" + h + ":" + min + ":00')";

                    out.print("{ title  : "
                            + "'" + dispo.getLibelleActivite() + "',"
                            + "start          : " + daD + ", "
                            + "end          : " + daFi + ", "
                            + "backgroundColor: '#f56954', borderColor    : '#f56954' },");
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

 //Date picker
                $('#datepicker1').datepicker({
                    autoclose: true
                })

                //Date range picker
                $('#reservation').daterangepicker()
                //Date range picker with time picker
                $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'})
                //Date range as a button
                $('#daterange-btn').daterangepicker(
                        {
                            ranges: {
                                'Today': [moment(), moment()],
                                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                                'This Month': [moment().startOf('month'), moment().endOf('month')],
                                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                            },
                            startDate: moment().subtract(29, 'days'),
                            endDate: moment()
                        },
                        function (start, end) {
                            $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
                        }
                )

                //Timepicker
                $('.timepicker').timepicker({
                    showInputs: false
                })
            })
        </script>

    </body>
</html>