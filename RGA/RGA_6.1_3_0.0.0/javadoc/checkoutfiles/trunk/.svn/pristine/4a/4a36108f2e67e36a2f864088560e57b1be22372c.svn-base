CREATE OR REPLACE Type Body MSConcatImpl Is

  Static Function ODCIAggregateInitialize(io_SrcContext In Out MSConcatImpl)
    Return Number Is
  Begin
    io_SrcContext           := MSConcatImpl(Null, Null);
    io_SrcContext.Delimiter := ',';
    Return ODCIConst.Success;
  End ODCIAggregateInitialize;

  Member Function ODCIAggregateIterate(Self  In Out MSConcatImpl,
                                       Value In Varchar2) Return Number Is
  Begin
    If Value Is Not Null Then
      If Self.ResultString Is Null Then
        Self.ResultString := Self.ResultString || Value;
      Else
        Self.ResultString := Self.ResultString || Self.Delimiter || Value;
      End If;
    End If;
    Return ODCIConst.Success;
  End ODCIAggregateIterate;

  Member Function ODCIAggregateTerminate(Self          In MSConcatImpl,
                                         o_ReturnValue Out Varchar2,
                                         i_Flags       In Number)
    Return Number Is
  Begin
    o_Returnvalue := Self.ResultString;
    Return ODCIConst.Success;
  End ODCIAggregateTerminate;

  Member Function ODCIAggregateMerge(Self   In Out MSConcatImpl,
                                     i_Ctx2 In MSConcatImpl) Return Number Is
  Begin
    If Self.ResultString Is Null And i_Ctx2.ResultString Is Not Null Then
      Self.ResultString := i_Ctx2.ResultString;
    Elsif Self.ResultString Is Not Null And i_Ctx2.ResultString Is Not Null Then
      Self.ResultString := Self.ResultString || Self.Delimiter ||
                           i_Ctx2.ResultString;
    End If;
    Return ODCIConst.Success;
  End ODCIAggregateMerge;

End;
 